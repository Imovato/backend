import * as cdk from 'aws-cdk-lib';
import { Duration, RemovalPolicy, Stack, StackProps } from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as cloudfront from 'aws-cdk-lib/aws-cloudfront';
import * as origins from 'aws-cdk-lib/aws-cloudfront-origins';
import * as cloudwatch from 'aws-cdk-lib/aws-cloudwatch';
import * as docdb from 'aws-cdk-lib/aws-docdb';
import * as ecr from 'aws-cdk-lib/aws-ecr';
import * as ecs from 'aws-cdk-lib/aws-ecs';
import * as ec2 from 'aws-cdk-lib/aws-ec2';
import * as elbv2 from 'aws-cdk-lib/aws-elasticloadbalancingv2';
import * as logs from 'aws-cdk-lib/aws-logs';
import * as mq from 'aws-cdk-lib/aws-amazonmq';
import * as rds from 'aws-cdk-lib/aws-rds';
import * as secretsmanager from 'aws-cdk-lib/aws-secretsmanager';
import * as servicediscovery from 'aws-cdk-lib/aws-servicediscovery';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as budgets from 'aws-cdk-lib/aws-budgets';

export interface ImovatoDevelopStackProps extends StackProps {
  environment: 'develop';
  imageTag: string;
  desiredCount: number;
  budgetUsd: number;
}

export class ImovatoDevelopStack extends Stack {
  constructor(scope: Construct, id: string, props: ImovatoDevelopStackProps) {
    super(scope, id, props);

    const envName = props.environment;
    const commonTags = { Project: 'imovato', Environment: envName, ManagedBy: 'cdk' };
    Object.entries(commonTags).forEach(([key, value]) => cdk.Tags.of(this).add(key, value));

    const vpc = new ec2.Vpc(this, 'Vpc', {
      maxAzs: 2,
      natGateways: 0,
      subnetConfiguration: [
        { name: 'public', subnetType: ec2.SubnetType.PUBLIC, cidrMask: 24 },
        { name: 'isolated', subnetType: ec2.SubnetType.PRIVATE_ISOLATED, cidrMask: 24 }
      ]
    });

    const cluster = new ecs.Cluster(this, 'Cluster', { vpc, containerInsightsV2: ecs.ContainerInsights.ENABLED });
    const namespace = new servicediscovery.PrivateDnsNamespace(this, 'ServiceNamespace', {
      name: `${envName}.imovato.local`,
      vpc,
      description: 'Private service discovery namespace for the Imovato develop environment'
    });

    const repositoryNames = ['gateway', 'discovery', 'cadastral', 'transacional'];
    const repositories = new Map<string, ecr.Repository>();
    for (const name of repositoryNames) {
      repositories.set(name, new ecr.Repository(this, `${name}Repository`, {
        repositoryName: `imovato/${envName}/${name}`,
        imageTagMutability: ecr.TagMutability.IMMUTABLE,
        imageScanOnPush: true,
        encryption: ecr.RepositoryEncryption.AES_256,
        lifecycleRules: [{ maxImageCount: 8 }],
        removalPolicy: RemovalPolicy.RETAIN
      }));
    }

    const imagesBucket = new s3.Bucket(this, 'ImagesBucket', {
      blockPublicAccess: s3.BlockPublicAccess.BLOCK_ALL,
      encryption: s3.BucketEncryption.S3_MANAGED,
      enforceSSL: true,
      versioned: true,
      lifecycleRules: [{ noncurrentVersionExpiration: Duration.days(30), abortIncompleteMultipartUploadAfter: Duration.days(7) }],
      removalPolicy: RemovalPolicy.RETAIN
    });
    const imageDistribution = new cloudfront.Distribution(this, 'ImagesDistribution', {
      defaultBehavior: {
        origin: origins.S3BucketOrigin.withOriginAccessControl(imagesBucket),
        viewerProtocolPolicy: cloudfront.ViewerProtocolPolicy.REDIRECT_TO_HTTPS,
        cachePolicy: cloudfront.CachePolicy.CACHING_OPTIMIZED
      },
      enabled: true,
      comment: `Imovato ${envName} image distribution`
    });

    const taskSecurityGroup = new ec2.SecurityGroup(this, 'TaskSecurityGroup', { vpc, description: 'Imovato ECS tasks' });
    taskSecurityGroup.addIngressRule(taskSecurityGroup, ec2.Port.tcp(8761), 'Eureka traffic between services');
    taskSecurityGroup.addIngressRule(taskSecurityGroup, ec2.Port.tcp(5757), 'Cadastral traffic between services');
    taskSecurityGroup.addIngressRule(taskSecurityGroup, ec2.Port.tcp(5858), 'Transacional traffic between services');

    const rdsSecurityGroup = new ec2.SecurityGroup(this, 'RdsSecurityGroup', { vpc, description: 'PostgreSQL access from Imovato ECS tasks' });
    rdsSecurityGroup.addIngressRule(taskSecurityGroup, ec2.Port.tcp(5432), 'PostgreSQL from ECS');
    const postgresSecret = new rds.DatabaseSecret(this, 'PostgresSecret', { username: 'imovato' });
    const postgres = new rds.DatabaseInstance(this, 'Postgres', {
      engine: rds.DatabaseInstanceEngine.postgres({ version: rds.PostgresEngineVersion.VER_16_4 }),
      instanceType: ec2.InstanceType.of(ec2.InstanceClass.T4G, ec2.InstanceSize.MICRO),
      vpc,
      vpcSubnets: { subnetType: ec2.SubnetType.PRIVATE_ISOLATED },
      securityGroups: [rdsSecurityGroup],
      credentials: rds.Credentials.fromSecret(postgresSecret),
      databaseName: 'transacional',
      allocatedStorage: 20,
      maxAllocatedStorage: 40,
      storageEncrypted: true,
      multiAz: false,
      publiclyAccessible: false,
      backupRetention: Duration.days(1),
      deletionProtection: false,
      removalPolicy: RemovalPolicy.SNAPSHOT
    });

    const documentDbSecurityGroup = new ec2.SecurityGroup(this, 'DocumentDbSecurityGroup', { vpc, description: 'DocumentDB access from Imovato ECS tasks' });
    documentDbSecurityGroup.addIngressRule(taskSecurityGroup, ec2.Port.tcp(27017), 'DocumentDB from ECS');
    const documentDbSecret = new docdb.DatabaseSecret(this, 'DocumentDbSecret', { username: 'imovato' });
    const documentDb = new docdb.DatabaseCluster(this, 'DocumentDb', {
      engineVersion: '5.0.0',
      instanceType: ec2.InstanceType.of(ec2.InstanceClass.T4G, ec2.InstanceSize.MEDIUM),
      instances: 1,
      vpc,
      vpcSubnets: { subnetType: ec2.SubnetType.PRIVATE_ISOLATED },
      securityGroup: documentDbSecurityGroup,
      masterUser: { username: 'imovato', password: documentDbSecret.secretValueFromJson('password') },
      backup: { preferredWindow: '03:00-03:30', retention: Duration.days(1) },
      deletionProtection: false,
      removalPolicy: RemovalPolicy.SNAPSHOT
    });

    const rabbitSecurityGroup = new ec2.SecurityGroup(this, 'RabbitSecurityGroup', { vpc, description: 'Amazon MQ access from Imovato ECS tasks' });
    rabbitSecurityGroup.addIngressRule(taskSecurityGroup, ec2.Port.tcp(5671), 'RabbitMQ TLS from ECS');
    const rabbitSecret = new secretsmanager.Secret(this, 'RabbitSecret', {
      generateSecretString: {
        secretStringTemplate: JSON.stringify({ username: 'imovato' }),
        generateStringKey: 'password',
        excludePunctuation: true
      }
    });
    const rabbitBroker = new mq.CfnBroker(this, 'RabbitBroker', {
      brokerName: `imovato-${envName}-rabbitmq`,
      engineType: 'RABBITMQ',
      engineVersion: '3.13',
      hostInstanceType: 'mq.m7g.medium',
      publiclyAccessible: false,
      deploymentMode: 'SINGLE_INSTANCE',
      users: [{ username: rabbitSecret.secretValueFromJson('username').unsafeUnwrap(), password: rabbitSecret.secretValueFromJson('password').unsafeUnwrap() }],
      logs: { general: true },
      securityGroups: [rabbitSecurityGroup.securityGroupId],
      subnetIds: [vpc.isolatedSubnets[0].subnetId]
    });

    const jwtSecret = new secretsmanager.Secret(this, 'JwtSecret', {
      generateSecretString: { excludePunctuation: true, passwordLength: 64 }
    });
    const eurekaSecret = new secretsmanager.Secret(this, 'EurekaSecret', {
      generateSecretString: {
        secretStringTemplate: JSON.stringify({ username: 'imovato' }),
        generateStringKey: 'password',
        excludePunctuation: true
      }
    });

    const logGroup = new logs.LogGroup(this, 'ApplicationLogs', {
      logGroupName: `/imovato/${envName}/application`,
      retention: logs.RetentionDays.ONE_WEEK,
      removalPolicy: RemovalPolicy.RETAIN
    });

    const createTask = (
      name: string,
      port: number,
      environment: Record<string, string>,
      secrets: Record<string, ecs.Secret> = {},
      secretReads: secretsmanager.ISecret[] = [],
      imagesAccess = false
    ) => {
      const definition = new ecs.FargateTaskDefinition(this, `${name}Task`, {
        cpu: 512,
        memoryLimitMiB: 1024,
        runtimePlatform: { cpuArchitecture: ecs.CpuArchitecture.ARM64, operatingSystemFamily: ecs.OperatingSystemFamily.LINUX }
      });
      const container = definition.addContainer(`${name}Container`, {
        image: ecs.ContainerImage.fromEcrRepository(repositories.get(name)!, props.imageTag),
        logging: ecs.LogDrivers.awsLogs({ streamPrefix: name, logGroup }),
        environment: { ...environment, SPRING_PROFILES_ACTIVE: envName, PORT: String(port) },
        secrets
      });
      container.addPortMappings({ containerPort: port, protocol: ecs.Protocol.TCP });
      if (imagesAccess) {
        imagesBucket.grantReadWrite(definition.taskRole);
      }
      secretReads.forEach(secret => secret.grantRead(definition.executionRole!));
      return { definition, container };
    };

    const rabbitEndpoint = rabbitBroker.attrAmqpEndpoints[0];
    const rabbitHost = cdk.Fn.select(0, cdk.Fn.split(':', cdk.Fn.select(1, cdk.Fn.split('://', rabbitEndpoint))));
    const commonEureka = { EUREKA_URL: 'http://discovery.' + namespace.namespaceName + ':8761/eureka' };
    const discoveryTask = createTask('discovery', 8761, {}, {
      EUREKA_USERNAME: ecs.Secret.fromSecretsManager(eurekaSecret, 'username'),
      EUREKA_PASSWORD: ecs.Secret.fromSecretsManager(eurekaSecret, 'password')
    }, [eurekaSecret]);
    const discoveryService = new ecs.FargateService(this, 'DiscoveryService', {
      cluster, taskDefinition: discoveryTask.definition, desiredCount: props.desiredCount,
      minHealthyPercent: 0, maxHealthyPercent: 100,
      assignPublicIp: true, vpcSubnets: { subnetType: ec2.SubnetType.PUBLIC }, securityGroups: [taskSecurityGroup],
      cloudMapOptions: { name: 'discovery', cloudMapNamespace: namespace },
      circuitBreaker: { enable: true, rollback: true }
    });

    const cadastralTask = createTask('cadastral', 5757, {
      ...commonEureka,
      MONGO_HOST: documentDb.clusterEndpoint.hostname,
      MONGO_PORT: '27017', MONGO_DATABASE: 'cadastral',
      RABBITMQ_HOST: rabbitHost,
      RABBITMQ_PORT: '5671', IMOVATO_IMAGES_BUCKET: imagesBucket.bucketName,
      IMOVATO_IMAGES_PUBLIC_BASE_URL: `https://${imageDistribution.distributionDomainName}`
    }, {
      MONGO_USERNAME: ecs.Secret.fromSecretsManager(documentDbSecret, 'username'),
      MONGO_PASSWORD: ecs.Secret.fromSecretsManager(documentDbSecret, 'password'),
      RABBITMQ_USERNAME: ecs.Secret.fromSecretsManager(rabbitSecret, 'username'),
      RABBITMQ_PASSWORD: ecs.Secret.fromSecretsManager(rabbitSecret, 'password'),
      JWT_SECRET: ecs.Secret.fromSecretsManager(jwtSecret),
      EUREKA_PASSWORD: ecs.Secret.fromSecretsManager(eurekaSecret, 'password')
    }, [documentDbSecret, rabbitSecret, jwtSecret, eurekaSecret], true);
    new ecs.FargateService(this, 'CadastralService', {
      cluster, taskDefinition: cadastralTask.definition, desiredCount: props.desiredCount,
      minHealthyPercent: 0, maxHealthyPercent: 100,
      assignPublicIp: true, vpcSubnets: { subnetType: ec2.SubnetType.PUBLIC }, securityGroups: [taskSecurityGroup],
      cloudMapOptions: { name: 'cadastral', cloudMapNamespace: namespace },
      circuitBreaker: { enable: true, rollback: true }
    });

    const transacionalTask = createTask('transacional', 5858, {
      ...commonEureka,
      POSTGRES_HOST: postgres.dbInstanceEndpointAddress, POSTGRES_PORT: '5432', POSTGRES_DATABASE: 'transacional',
      RABBITMQ_HOST: rabbitHost, RABBITMQ_PORT: '5671',
      CADASTRAL_BASE_URL: 'http://cadastral.' + namespace.namespaceName + ':5757'
    }, {
      POSTGRES_USERNAME: ecs.Secret.fromSecretsManager(postgresSecret, 'username'),
      POSTGRES_PASSWORD: ecs.Secret.fromSecretsManager(postgresSecret, 'password'),
      RABBITMQ_USERNAME: ecs.Secret.fromSecretsManager(rabbitSecret, 'username'),
      RABBITMQ_PASSWORD: ecs.Secret.fromSecretsManager(rabbitSecret, 'password'),
      JWT_SECRET: ecs.Secret.fromSecretsManager(jwtSecret),
      EUREKA_PASSWORD: ecs.Secret.fromSecretsManager(eurekaSecret, 'password')
    }, [postgresSecret, rabbitSecret, jwtSecret, eurekaSecret]);
    new ecs.FargateService(this, 'TransacionalService', {
      cluster, taskDefinition: transacionalTask.definition, desiredCount: props.desiredCount,
      minHealthyPercent: 0, maxHealthyPercent: 100,
      assignPublicIp: true, vpcSubnets: { subnetType: ec2.SubnetType.PUBLIC }, securityGroups: [taskSecurityGroup],
      cloudMapOptions: { name: 'transacional', cloudMapNamespace: namespace },
      circuitBreaker: { enable: true, rollback: true }
    });

    const gatewayTask = createTask('gateway', 8080, { ...commonEureka }, {
      EUREKA_USERNAME: ecs.Secret.fromSecretsManager(eurekaSecret, 'username'),
      EUREKA_PASSWORD: ecs.Secret.fromSecretsManager(eurekaSecret, 'password')
    }, [eurekaSecret]);
    const gatewayService = new ecs.FargateService(this, 'GatewayService', {
      cluster, taskDefinition: gatewayTask.definition, desiredCount: props.desiredCount,
      minHealthyPercent: 0, maxHealthyPercent: 100,
      assignPublicIp: true, vpcSubnets: { subnetType: ec2.SubnetType.PUBLIC }, securityGroups: [taskSecurityGroup],
      cloudMapOptions: { name: 'gateway', cloudMapNamespace: namespace },
      circuitBreaker: { enable: true, rollback: true }
    });

    const loadBalancer = new elbv2.ApplicationLoadBalancer(this, 'GatewayLoadBalancer', { vpc, internetFacing: true });
    const loadBalancerSecurityGroup = loadBalancer.connections.securityGroups[0];
    taskSecurityGroup.addIngressRule(loadBalancerSecurityGroup, ec2.Port.tcp(8080), 'Public gateway traffic from ALB');
    const listener = loadBalancer.addListener('GatewayListener', { port: 80, open: true });
    const gatewayTargets = listener.addTargets('GatewayTargets', {
      port: 8080, targets: [gatewayService],
      healthCheck: { path: '/actuator/health', healthyHttpCodes: '200-399', interval: Duration.seconds(30) }
    });

    new cloudwatch.Alarm(this, 'GatewayUnhealthyHosts', {
      metric: gatewayTargets.metrics.unhealthyHostCount({ period: Duration.minutes(1), statistic: 'Maximum' }),
      threshold: 1, evaluationPeriods: 2, comparisonOperator: cloudwatch.ComparisonOperator.GREATER_THAN_OR_EQUAL_TO_THRESHOLD
    });
    new budgets.CfnBudget(this, 'MonthlyBudget', {
      budget: {
        budgetName: `imovato-${envName}-monthly`, budgetType: 'COST', timeUnit: 'MONTHLY',
        budgetLimit: { amount: props.budgetUsd, unit: 'USD' }, costFilters: { TagKeyValue: [`user:Environment$${envName}`] }
      }
    });

    new cdk.CfnOutput(this, 'GatewayUrl', { value: `http://${loadBalancer.loadBalancerDnsName}` });
    new cdk.CfnOutput(this, 'ImagesBaseUrl', { value: `https://${imageDistribution.distributionDomainName}` });
    new cdk.CfnOutput(this, 'PostgresEndpoint', { value: postgres.dbInstanceEndpointAddress });
    new cdk.CfnOutput(this, 'DocumentDbEndpoint', { value: documentDb.clusterEndpoint.hostname });
    new cdk.CfnOutput(this, 'RabbitMqEndpoints', { value: cdk.Fn.join(',', rabbitBroker.attrAmqpEndpoints) });
    new cdk.CfnOutput(this, 'EcrRepositories', { value: repositoryNames.map(name => repositories.get(name)!.repositoryUri).join(',') });
  }
}
