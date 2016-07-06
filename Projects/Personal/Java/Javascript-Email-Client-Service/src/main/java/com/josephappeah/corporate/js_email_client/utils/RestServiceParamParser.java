package com.josephappeah.corporate.js_email_client.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestServiceParamParser {
	private static final Logger logger = LoggerFactory.getLogger(RestServiceParamParser.class);
	public static Map<String,Object> getParams(JSONObject params){
		return parse(params);
	}
	
	private static Map<String,Object> parse(JSONObject params){
		Map<String,Object> parsedData = new HashMap<String,Object>();
		try{
			parsedData.put("sender", params.get("sender"));
		}catch(Exception e){
			throw e;
		}
		
		try{
			parsedData.put("password", params.get("password"));
		}catch(Exception e){
			throw e;
		}
		
		try{
			parsedData.put("subject", params.get("subject"));
		}catch(Exception e){
			parsedData.put("subject",""); 
		}
		
		try{
			parsedData.put("recepient", params.get("recepient"));
		}catch(Exception e){
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
		}
		
		try{
			parsedData.put("host", params.get("host"));
		}catch(Exception e){
			parsedData.put("host","No Host"); 
		}
		
		
		return parsedData;
	}
}
	