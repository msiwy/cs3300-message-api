#!/bin/bash

## Install awscli
# pip install awscli

# aws configure --profile cs3300 
# region: us-east-1
# output: json


## Get the bucket to deploy to
# aws elasticbeanstalk describe-application-versions --application-name barista_server --output=json --profile cs3300

echo "##### Uploading war to S3"
aws s3 cp ./build/libs/restserver-1.0-SNAPSHOT.war s3://elasticbeanstalk-us-east-1-433352823535/restserver.war --profile cs3300

VERSION=`whoami`-`date +"%m-%d-%y_%H-%M"`

echo "##### Creating new application version $VERSION"
aws elasticbeanstalk create-application-version --application-name p1server --version-label $VERSION --source-bundle S3Bucket=elasticbeanstalk-us-east-1-433352823535,S3Key=restserver.war --profile cs3300

## Get environment to update
# aws elasticbeanstalk describe-environments --application-name p1server --output=json --profile cs3300

echo "##### Updating application version $VERSION"
aws elasticbeanstalk update-environment --environment-name cs3300 --version-label $VERSION --profile cs3300

echo "##### Done."
echo "## Check status using:  aws elasticbeanstalk describe-environments --environment-name cs3300 --profile cs3300" 
