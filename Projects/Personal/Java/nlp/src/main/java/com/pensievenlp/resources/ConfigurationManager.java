package com.pensievenlp.resources;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationManager {

	private static String configFilePath = "src/main/resources/PensieveNLP.properties";
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);
	
	private static Properties props = new Properties();
	private InputStream configStream = null;
	
	public  ConfigurationManager() throws Exception{
		
		try{
			
			 configStream = new FileInputStream(configFilePath);
			 for(Entry<?, ?> entry : props.entrySet()){logger.debug(entry.getKey() + " :: " + entry.getValue());}
		 
		} catch (Exception e){
			
			logger.error("Failed to obtain config inpustream at {}. Ensure filepath is correct", configFilePath);
		}
		
		try{
			props.load(configStream);
		} catch (Exception e){
			
		}
	}
	
	public Properties getConfigs(){
		return ConfigurationManager.props;
	}
	
}
