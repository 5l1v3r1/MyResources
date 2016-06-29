package com.rovicorp.compare_rm_images_service.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rovicorp.compare_rm_images_service.interfaces.DependencyInjector;

public class DependencyHandler implements DependencyInjector{
	
	
	Properties props = new Properties();
	
	private static final Logger logger = LoggerFactory.getLogger(DependencyHandler.class);
	
	private 	String 		portnumber 			= null;
	private 	String 		prod_recent_query 	= null;
	private 	String 		aws_uat_server_url 	= null;
	private 	String 		aws_dev_server_url 	= null;
	private 	String 		aws_qa_server_url 	= null;
	private 	String 		tulsa_server_url 	= null;
	private 	String 		qa_specific_query 	= null;
	private 	String 		prod_random_query 	= null;
	private 	String 		prod_specific_query = null;
	private 	String 		prod_env_server 	= null;
	private		String		qa_env_server		= null;
	private 	Integer 	numberofdayspassed 	= null;
    private 	String 		password 			= null;
    private 	String 		username 			= null;
     
	
    public DependencyHandler() throws Exception {
    	
		logger.info("Looking up ROVI_BASE");
	    String rovi_base	=	null;
	    
	    try{
	    	rovi_base	=	System.getenv("ROVI_BASE");
		    logger.info("Found ROVI_BASE:{}",rovi_base);
	    }catch(Exception e){
	    	logger.error("Launch Error: ROVI_BASE is not defined in this environment",e);
	    	throw e;
	    }

	    
	    String propertiesPath	=	rovi_base+ File.separator+"conf"+File.separator+"compare_rm_images.properties";

	    
		try {
			props.load(new FileInputStream(propertiesPath));
			
			logger.debug("xxxxxxxxx Properties xxxxxxxxx");
			Enumeration<Object> propsKeys = props.keys();
			while(propsKeys.hasMoreElements()){
				String property 	= 	(String)propsKeys.nextElement();
				logger.info("{}:{}", property,props.getProperty(property));
			}
		}catch(Exception e){
			logger.error("Failed to load properties from {}",propertiesPath,e);
			throw e;
		}
		
		//Application Server Params
		portnumber 				= 		(String) props.get("port_number");
		
		//MSSQL Connector Params
        username 				= 		(String) props.get("mssql_username");
		password 				= 		(String) props.get("mssql_password");
		prod_env_server 		= 		(String) props.get("tulsa_prod_server");
		qa_env_server			=		(String) props.get("tulsa_qa_sever");
		
		//Query Params
		prod_random_query 		= 		(String) props.get("prod_random_image_query");
		prod_specific_query 	= 		(String) props.get("prod_specific_image_query");
		prod_recent_query 		= 		(String) props.get("prod_recent_image_query");
		qa_specific_query		=		(String) props.get("qa_specific_image_query");
		numberofdayspassed 		=  		Integer.parseInt((String)props.get("num_of_days_passed"));
		
		//Server URL Params
		aws_uat_server_url		=		(String) props.get("aws_uat_server_url");
		aws_dev_server_url		=		(String) props.get("aws_dev_server_url");
		aws_qa_server_url		=		(String) props.get("aws_qa_server_url");
		tulsa_server_url		=		(String) props.get("tulsa_server_url");
		
	}
    
    

	@Override
	public String getPortNumber() {
		return portnumber;
	}


	@Override
	public String getProdServerName() {
		return prod_env_server;
	}

        
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public String getQAServerName() {
		return qa_env_server;
	}



	@Override
	public String getProdRandomImageQuery() {
		return prod_random_query;
	}



	@Override
	public String getQASpecificImageQuery() {
		return qa_specific_query;
	}



	@Override
	public String getProdSpecificImageQuery() {
		return prod_specific_query;
	}



	@Override
	public String getProdRecentImageQuery() {
		return prod_recent_query;
	}



	@Override
	public int getProdRecentNumberOfDays() {
		return numberofdayspassed;
	}



	@Override
	public String getAwsUatUrl() {
		return aws_uat_server_url;
	}



	@Override
	public String getAwsDevUrl() {
		return aws_dev_server_url;
	}



	@Override
	public String getAwsQaUrl() {
		return aws_qa_server_url;
	}



	@Override
	public String getTulsaServerUrl() {
		return tulsa_server_url;
	}


	
}
