package com.amazonaws.lambda.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
public class DynamoDBConfig {

	public static AmazonDynamoDB getAmazonDynamoDB() {
		final AWSCredentials credentials = new BasicAWSCredentials("AKIAWURPD4VTWKA4HPXE",
				"SveFHI/WweNCkEVgBAB5n1rgmUH9od4IlOUJSo5D");
		final AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider)
				.withRegion(Regions.AP_SOUTHEAST_1).build();
		return client;

	}

}
