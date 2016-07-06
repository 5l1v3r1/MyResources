package com.josephappeah.corporate.js_email_client.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestServiceParamParser {
	private static final Logger logger = LoggerFactory.getLogger(RestServiceParamParser.class);

	public static Map<String,Object> getParams(JSONObject params){
		logger.debug("Attempting to parse query params from: {}.",params.toString());
		return parse(params);
	}
	
	private static Map<String,Object> parse(JSONObject params){
		Map<String,Object> parsedData = new HashMap<String,Object>();
		try{
			parsedData.put("sender", params.get("sender"));
		}catch(Exception e){
			logger.error("Failed to obtain sender",e);
			throw e;
		}
		
		try{
			parsedData.put("password", params.get("password"));
		}catch(Exception e){
			logger.error("Failed to obtain password",e);
			throw e;	
		}
		
		try{
			parsedData.put("subject", params.get("subject"));
		}catch(Exception e){
			parsedData.put("subject",""); 
			logger.error("No subject found. value defaulted to \"\" ");
		}
		
		try{
			parsedData.put("recepient", params.get("recepient"));
		}catch(Exception e){
			logger.error("Failed to obtain recepient",e);
			throw e;
		}
		
		try{
			parsedData.put("message", params.get("message"));
		}catch(Exception e){
			parsedData.put("message","No Message Was Provided"); 
		}
		
		try{
			parsedData.put("attachment", params.get("attachment").toString());
		}catch(Exception e){
			parsedData.put("attachment",""); 
			logger.error("No attachment found. value defaulted to \"\" ");
		}
		
		try{
			parsedData.put("host", params.get("host"));
		}catch(Exception e){
			parsedData.put("host","No Host"); 
			logger.error("No host found. value defaulted to 'No Host' ");
		}
		
		
		return parsedData;
	}
}
	