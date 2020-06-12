package com.amazonaws.lambda.demo.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "result")
public class Result  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String messageid;
	private String querystring;
	private String response;
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(String messageid, String querystring, String response) {
		super();
		this.messageid = messageid;
		this.querystring = querystring;
		this.response = response;
	}
	@DynamoDBHashKey(attributeName = "messageid")
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	public String getQuerystring() {
		return querystring;
	}
	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

}

