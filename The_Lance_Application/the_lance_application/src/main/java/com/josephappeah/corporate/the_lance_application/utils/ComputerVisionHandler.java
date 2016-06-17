package com.josephappeah.corporate.the_lance_application.utils;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.the_lance_application.interfaces.GetImageMetadata;


public class ComputerVisionHandler implements GetImageMetadata{
	
	private static final Logger logger = LoggerFactory.getLogger(ImageSearchHandler.class);
	private static String 				computervisionurl 		= null;
	private static String 				computervisionkey  		= null;
	private static URL 					url 					= null;
	private static HttpURLConnection 	connection 				= null;
	private static InputStream			responsestream			= null;
	private static String 				responsedata			= null;
	private byte[]						image					= null;
	private static DataOutputStream 	requestoutputstream 	= null;

	
	public ComputerVisionHandler(DependencyHandler di){
		computervisionkey	= di.getImageSearchKey();
		computervisionurl 	= di.getImageSearchURL();
	}
		
	
	@Override
	public void setUpURLConnnection(byte[] image) {
		this.image = image;
		
		try{
			logger.debug("Creating request url for image '{}'");
			url = new URL(computervisionurl);
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
			connection.setRequestProperty("Ocp-Apim-Subscription-Key",computervisionkey);	
		}catch(Exception e){
			logger.debug("Failed to configure connection headers.",e);
		}
		
	}
	
	
	
	@Override
	public void getRequestResponseStream(){
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

	
	@Override
	public void analyseResponseStream() {
		
		try{
			logger.debug("Converting response data to string.");
			responsedata = IOUtils.toString(responsestream);
		} catch(Exception e){
			logger.debug("Failed to convert response data to string.",e);
		}
		
	}


	@Override
	public String execute(String computervisionquery) {
		setUpURLConnnection(computervisionquery);
		getRequestResponseStream();
		analyseResponseStream();
		
		try{
			logger.debug("Returning image search data as arraylist.");
			return responsedata;
		}catch (Exception e){
			logger.debug("Failed to return image search data as arraylist.");
		}
		return null;
	}


	@Override
	public void setUpURLConnnection(String query) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String execute(byte[] image) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
