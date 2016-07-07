package com.josephappeah.corporate.js_email_client.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class MultipartFormParamParser {
	private static final Logger logger = LoggerFactory.getLogger(MultipartFormParamParser.class);

	public static Map<String,Object> getParams(MultipartFormDataInput input){	
		try {
			return parse(input);
		} catch (Exception e) {
		}
		return null;
	}
	
	private static Map<String,Object> parse(MultipartFormDataInput input) throws Exception{
		Map<String,Object> parsedData = new HashMap<String,Object>();
		Map<String, List <InputPart>> params = null;
		try{	
			params = input.getFormDataMap();
		}catch(Exception e){
			throw e;
		}
		
		try{
			parsedData.put("sender", params.get("sender").get(0).getBodyAsString());
		}catch(Exception e){
			logger.error("Failed to obtain sender",e);
			throw e;
		}
		
		try{
			parsedData.put("password", params.get("password").get(0).getBodyAsString());
		}catch(Exception e){
			logger.error("Failed to obtain password",e);
			throw e;	
		}
		
		try{
			parsedData.put("subject", params.get("subject").get(0).getBodyAsString());
		}catch(Exception e){
			parsedData.put("subject",""); 
			logger.error("No subject found. value defaulted to \"\" ");
		}
		
		try{
			parsedData.put("recepient", params.get("recepient").get(0).getBodyAsString());
		}catch(Exception e){
			logger.error("Failed to obtain recepient",e);
			throw e;
		}
		
		try{
			parsedData.put("message", params.get("message").get(0).getBodyAsString());
		}catch(Exception e){
			parsedData.put("message","No Message Was Provided"); 
		}
		
		try{
			List<InputPart> inputParts = params.get("attachment");
			
			for (InputPart inputPart : inputParts) {

					MultivaluedMap<String, String> header = inputPart.getHeaders();
					String fileName = getFileName(header);

					//convert the uploaded file to inputstream
					InputStream inputStream = (InputStream) inputPart.getBody(InputStream.class,null);

					byte [] bytes = IOUtils.toByteArray(inputStream);
					
					File attachment = File.createTempFile(fileName,FilenameUtils.getExtension(fileName));
					FileUtils.writeByteArrayToFile(attachment, bytes);	
					parsedData.put("attachment", attachment);
				}
			

		}catch(Exception e){
			parsedData.put("attachment",null); 
			logger.error("No attachment found. value defaulted to null");
		}
		
		try{
			parsedData.put("host", params.get("host").get(0).getBodyAsString());
		}catch(Exception e){
			parsedData.put("host","No Host"); 
			logger.error("No host found. value defaulted to 'No Host' ");
		}
		
		
		return parsedData;
	}
	
	
	private static String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
	
}
	