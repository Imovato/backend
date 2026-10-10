#!/usr/bin/env node
import * as cdk from 'aws-cdk-lib';
import { ImovatoDevelopStack } from '../lib/imovato-develop-stack.js';

const app = new cdk.App();
const environment = String(app.node.tryGetContext('environment') ?? 'develop');
const region = String(app.node.tryGetContext('region') ?? process.env.CDK_DEFAULT_REGION ?? 'us-east-1');

if (environment !== 'develop') {
  throw new Error(`This stack is intentionally limited to the develop environment. Received: ${environment}`);
}

new ImovatoDevelopStack(app, 'ImovatoDevelopStack', {
  env: { account: process.env.CDK_DEFAULT_ACCOUNT, region },
  environment,
  imageTag: String(app.node.tryGetContext('imageTag') ?? 'develop'),
  desiredCount: Number(app.node.tryGetContext('desiredCount') ?? 1),
  budgetUsd: Number(app.node.tryGetContext('budgetUsd') ?? 100),
  description: 'Imovato develop environment: ECS, RDS, DocumentDB, Amazon MQ, S3 and CloudFront.'
});
