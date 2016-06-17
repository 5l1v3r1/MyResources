package com.josephappeah.corporate.image_name_generator.modules;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.image_name_generator.interfaces.HttpRequestExecutor;

public class ComputerVisionHttpHandler implements HttpRequestExecutor{
	private static final Logger logger = LoggerFactory.getLogger(ComputerVisionHttpHandler.class);
	private static Map<Object,Object> dependencies = null;
	private static URL url = null;
	private static HttpURLConnection connection = null;
	private static DataOutputStream  requestoutputstream  = null;
	private static InputStream responsestream =null;
	private static byte[] image = null;

	public void setDependencies(Properties dependencies) {
		ComputerVisionHttpHandler.dependencies = dependencies;
	}


	public void sendRequest() {
		try{
			logger.debug("Creating request url for image '{}'");
			url = new URL((String) (dependencies.get("computer_vision_url")));
			
		} catch(Exception e){
			logger.debug("failed to create request url",e);
		}

		
		try{
			logger.debug("Setting up httpurlconnection.");
			connection = (HttpURLConnection) url.openConnection();
		}catch (Exception e){
			logger.debug("failed to set up httpurlconnection.",e);
		}
		
		
		try{
			logger.debug("Configuring connection headers.");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Ocp-Apim-Subscription-Key",(String) dependencies.get("computer_vision_key"));	
		}catch(Exception e){
			logger.debug("Failed to configure connection headers.",e);
		}
		
		try{
			logger.debug("Sending computer visionn request.");
			requestoutputstream = new DataOutputStream(connection.getOutputStream());
			requestoutputstream.write(image);
			requestoutputstream.flush();
			requestoutputstream.close();
		}catch(Exception e){
			logger.debug("Failed to send computer visionn request.");
		}
		
		
		try{
			logger.debug("Eagerly obtaining response data.");
			responsestream = connection.getInputStream();
		}catch(Exception e){
			logger.debug("Failed to get response data.",e);
		}
		
		
		
	}

	public InputStream getResponseData() {
		return ComputerVisionHttpHandler.responsestream;
	}

	public void setRequestData(byte[] Byte) {
		ComputerVisionHttpHandler.image = Byte;
	}

}
