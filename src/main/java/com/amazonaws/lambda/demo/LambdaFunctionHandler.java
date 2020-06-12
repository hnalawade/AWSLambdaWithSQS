package com.amazonaws.lambda.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.lambda.demo.config.DynamoDBConfig;
import com.amazonaws.lambda.demo.utility.AllPossibleCombinations;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;

public class LambdaFunctionHandler implements RequestHandler<SQSEvent, String> {

	private static final Logger logger = LoggerFactory.getLogger(LambdaFunctionHandler.class);
	private JSONArray inputArray;

	@Override
	public String handleRequest(SQSEvent input, Context context) {
		logger.info("Input: " + input);
		List<SQSMessage> sqsMessages = input.getRecords();
		logger.info("sqsMessages: " + sqsMessages);

		sqsMessages.stream().forEach(message -> {
			try {
				logger.info("  Complete message " + message);
				logger.info("Message From Event " + message.getBody() + LocalDateTime.now());
				String messageId = message.getMessageId();
				inputArray = getParsedData(message.getBody());
				DynamoDB dynamoDB = new DynamoDB(DynamoDBConfig.getAmazonDynamoDB());
				logger.info("connectin established");

				Table table = dynamoDB.getTable("result");
				logger.info("Table identified");

				table.putItem(new Item().withPrimaryKey("messageid", messageId, "messagetext", message.getBody())
						.with("response", AllPossibleCombinations.generatePossibleCombinations(inputArray)));
				logger.info("Row inserted");

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		});

		return "Hi from lambda";
	}

	public JSONArray getParsedData(String request) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(request);
		logger.info("jsonObj ===" + jsonObj);
		JSONArray Records = (JSONArray) jsonObj.get("input");
		logger.info("Records ===" + Records);
		return Records;

	}

}
