# Imovato `develop` AWS infrastructure

This CDK app provisions the `develop` environment only. It creates ECR repositories, ECS/Fargate services, RDS PostgreSQL, Amazon DocumentDB, Amazon MQ for RabbitMQ, S3 + CloudFront image storage, CloudWatch logs/alarms, and a monthly budget.

## Prerequisites

- AWS CLI credentials for the target account.
- Node.js 20+ and Docker.
- AWS CDK CLI 2.x.
- A bootstrapped `us-east-1` environment.

## Deploy

From this directory:

```bash
npm ci
npm run build
cdk bootstrap aws://ACCOUNT_ID/us-east-1

# First create ECR and the managed dependencies without starting ECS tasks.
npx cdk deploy --context desiredCount=0 --require-approval broad

IMAGE_TAG="$(git -C .. rev-parse --short HEAD)"
ACCOUNT_ID="$(aws sts get-caller-identity --query Account --output text)"
REGION="us-east-1"
aws ecr get-login-password --region "$REGION" | docker login --username AWS --password-stdin "$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com"

# Build and push immutable images. The AWS environment remains develop; the tag is immutable.
for service in gateway discovery cadastral transacional; do
  repository="$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/imovato/develop/$service"
  docker build --platform linux/arm64 -t "$repository:$IMAGE_TAG" "../$service"
  docker push "$repository:$IMAGE_TAG"
done

# Start the develop services after all images exist.
npx cdk deploy --context imageTag="$IMAGE_TAG" --require-approval broad
```

The generated CloudFormation outputs contain the ALB URL, CloudFront image URL, and service endpoints. Configure the frontend to use `GatewayUrl`.

## Safety

- The stack is intentionally restricted to the `develop` environment.
- Stateful resources use snapshots on deletion.
- No migration of Heroku data is performed; this is a fresh develop environment.
- Review `cdk diff` and the estimated AWS cost before every deployment.
