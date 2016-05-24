package com.joeycorp.webapps.lanceapp.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joeycorp.webapp.lanceapp.modules.GetMetadata;

public class GetImageMetadata implements GetMetadata{
	
	private File file = new File("src/main/config/config.properties");
	private static final Logger logger = LoggerFactory.getLogger(GetImageMetadata.class);
	private Properties props = new Properties();

	/*
	public static void main(String[] args){
		GetImageMetadata p = new GetImageMetadata();
		try {
			System.out.println(p.executeImageSearchResult("boy"));
			System.out.println(p.executeComputerVision(new File("src/main/resources/image.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	@Override
	public String executeImageSearchResult(String queryparam) throws FileNotFoundException, IOException, MalformedURLException {
		logger.debug("loading properties from {}.",file.getPath());
		props.load(new FileInputStream(file));
		//props.forEach((key,value) -> logger.info("{}:{}",key,value));
		
		String imageURLs = "", requestlines  = null;
		InputStream requestStream   = null;
		HttpURLConnection connection = null;
		
		//split the bing url by the delimiter and add the query parameter
		logger.debug("getting bing api url components.");
		String[] bingURLComponents = props.get("image_search_url").toString().split("QUERYPARAMGOESHERE");
		
		logger.debug("forming request url ");
		String requestURL = bingURLComponents[0]+ "\""+ queryparam+"\""+ bingURLComponents[1];
		logger.debug(requestURL);
		URL url = new URL(requestURL);
		
		//setting up request
		try{
			logger.debug("setting up httpurlconnection.");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Ocp-Apim-Subscription-Key", (String) props.get("image_search_key"));

		} catch(Exception e){
			logger.debug("failed to set up httpurlconnection.");
			logger.debug(e.getMessage());
		}
		
		try{
			logger.debug("getting image URLs from Bing.");
			connection.setRequestMethod("GET");
			requestStream = connection.getInputStream();
			
			logger.debug("storing response data");
			BufferedReader r = new BufferedReader(new InputStreamReader(requestStream));
			while (((requestlines  = r.readLine()) != null)) {
				imageURLs+=requestlines;
			}
			
			
		} catch(Exception e){
			logger.debug("failed to get image URLs from Bing.");
			logger.debug(e.getMessage());
		}
		
		return imageURLs;
	}



	@Override
	public String executeComputerVision(File image) throws FileNotFoundException, IOException, MalformedURLException {
		HttpURLConnection connection = null;
		URL url = new URL((String) props.get("computer_vision_url"));
		
		String imageMetadata = "", requestlines  = null;
		InputStream requestStream   = null;

		
		//setting up request
		try{
			logger.debug("setting up httpurlconnection.");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Ocp-Apim-Subscription-Key", (String) props.get("computer_vision_key"));
			connection.setRequestProperty("Content-type", "application/octet-stream");
			connection.setDoOutput(true);
			connection.setUseCaches(false);

		} catch(Exception e){
			logger.debug("failed to set up httpurlconnection.");
			logger.debug(e.getMessage());
		}
		
		DataOutputStream postRequest = null;
		
		try{
			logger.debug("creating post request.");
			connection.setRequestMethod("POST");
			postRequest = new DataOutputStream(connection.getOutputStream());
			byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(image));
			postRequest.write(imageBytes);
		}catch(Exception e){
			logger.debug("failed to create post request.");
			logger.debug(e.getMessage());
		}
		
		try{
			logger.debug("sending post request.");		
			postRequest.flush();
			postRequest.close();
		}catch(Exception e){
			logger.debug("failed to send post request.");
			logger.debug(e.getMessage());
		}
		
		try{
			logger.debug("getting image metadata.");		
			requestStream = connection.getInputStream();
			logger.debug("storing response data");
			BufferedReader r = new BufferedReader(new InputStreamReader(requestStream));
			while (((requestlines  = r.readLine()) != null)) {
				imageMetadata+=requestlines;
			}
			
			
		} catch(Exception e){
			logger.debug("failed to get image URLs from Bing.");
			logger.debug(e.getMessage());
		}
		
		return imageMetadata;
	}
	
	
	

}
