
1)AWS command to deploy the stack to an account:

aws cloudformation deploy --template-file D:/AA/template.json --stack-name AWS-Assignment --region ap-southeast-1


2) template.json

{
  "AWSTemplateFormatVersion": "2010-09-09",
  "Resources": {
    "tableresult": {
      "Type": "AWS::DynamoDB::Table",
      "Properties": {
        "AttributeDefinitions": [
          {
            "AttributeName": "messageid",
            "AttributeType": "S"
          }
        ],
        "KeySchema": [
          {
            "AttributeName": "messageid",
            "KeyType": "HASH"
          }
        ],
        "ProvisionedThroughput": {
          "ReadCapacityUnits": "5",
          "WriteCapacityUnits": "5"
        }
      }
    },
    "queueMessageQueue": {
      "Type": "AWS::SQS::Queue",
      "Properties": {
        "DelaySeconds": "0",
        "MaximumMessageSize": "262144",
        "MessageRetentionPeriod": "345600",
        "ReceiveMessageWaitTimeSeconds": "0",
        "VisibilityTimeout": "30"
      }
    },
    "alarmresultReadCapacityUnitsLimitBasicAlarm": {
      "Type": "AWS::CloudWatch::Alarm",
      "Properties": {
        "ActionsEnabled": "true",
        "ComparisonOperator": "GreaterThanOrEqualToThreshold",
        "EvaluationPeriods": "5",
        "MetricName": "ConsumedReadCapacityUnits",
        "Namespace": "AWS/DynamoDB",
        "Period": "60",
        "Statistic": "Sum",
        "Threshold": "240.0",
        "AlarmActions": [
          "arn:aws:sns:ap-southeast-1:456439096679:dynamodb"
        ],
        "Dimensions": [
          {
            "Name": "TableName",
            "Value": "result"
          }
        ]
      }
    },
    "alarmresultWriteCapacityUnitsLimitBasicAlarm": {
      "Type": "AWS::CloudWatch::Alarm",
      "Properties": {
        "ActionsEnabled": "true",
        "ComparisonOperator": "GreaterThanOrEqualToThreshold",
        "EvaluationPeriods": "5",
        "MetricName": "ConsumedWriteCapacityUnits",
        "Namespace": "AWS/DynamoDB",
        "Period": "60",
        "Statistic": "Sum",
        "Threshold": "240.0",
        "AlarmActions": [
          "arn:aws:sns:ap-southeast-1:456439096679:dynamodb"
        ],
        "Dimensions": [
          {
            "Name": "TableName",
            "Value": "result"
          }
        ]
      }
    }
  },
  "Description": ""
}