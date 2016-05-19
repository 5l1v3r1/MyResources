package com.rovicorp.compare_rm_images.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateURLs {
	
	Map<String,Object> CF_URLs = new HashMap<String, Object>();//Contains <number++ , CF_MetaData>
	Map<String,Object> CPS_URLs = new HashMap<String,Object>();//Contains <number++ , CPS_MetaData>

	Map<String,Object> List = new HashMap<String,Object>(); //Contains<CF/CPS, CF_URLs/CPS_URLs>
	
	final static Logger logger = LoggerFactory.getLogger(GenerateURLs.class);
	
	
	public static void main(String[] args) throws Exception{	
		GenerateURLs generate = new GenerateURLs();
		generate.generate();
		generate.storeMetadata();
		//System.out.println(json.toString());
	}
	
	public void  generate() throws Exception{
		int count = 0;
		String eachLine;
		String URLFile = "src/main/resources/ImageURLs.txt";
		InputStream in = new FileInputStream(new File(URLFile));
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
		
        try {
        	logger.debug("Obtaining URLs from File: {}",URLFile);
			while((eachLine = reader.readLine())!= null){
				
				String 	cps_staticURL 		= "http://cps-static.rovicorp.com/2"+ eachLine;
				String 	cloudfrontURL		= "http://d7cks5tzzz8un.cloudfront.net/2"+ eachLine;
				
				Map<String,String> CF_MetaData = new HashMap<String,String>();// Contains<URL and Number Of Bytes>
				Map<String,String> CPS_MetaData = new HashMap<String,String>();// Contains<URL and Number Of Bytes>
				
				CF_MetaData.put("URL", cloudfrontURL);
				CF_MetaData.put("Bytes", Integer.toString(GetByteNumber.get(cloudfrontURL)));
				
				CPS_MetaData.put("URL", cps_staticURL);
				CPS_MetaData.put("Bytes", Integer.toString(GetByteNumber.get(cps_staticURL)));
				
				CF_URLs.put(Integer.toString(count), CF_MetaData);
				CPS_URLs.put(Integer.toString(count), CPS_MetaData);
				
				logger.debug("getting info for: {}",count);
				count++;
			}
		} catch (Exception e) {
			
			logger.debug(e.getMessage());
			
		}
        
        logger.info("Closing File: {}", URLFile);
		reader.close();
		
	}
	
	
	public void storeMetadata(){
		JSONObject json = new JSONObject();
		logger.info("Converting Map data to JSON");
		json.put("result", get());
		
		logger.info("Storing JSON to a local file");
		File file = new File("src/main/resources/imageMetadata.txt");
		try{
		FileUtils.writeStringToFile(file, json.toString());
		} catch (Exception e){
			logger.debug(e.getMessage());
			logger.debug("Failed to store metadata in file {}", file.toString());
		}
	}
	
	
	public Map<String,Object> get(){
		List.put("CF", CF_URLs);
		List.put("CPS", CPS_URLs);
		return List;
	}
	
	

}
