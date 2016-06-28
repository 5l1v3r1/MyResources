package com.rovicorp.rabbitmq_s3_push.helperclasses;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rovicorp.rabbitmq_s3_push.interfaces.ImageStore;

public class S3Storage implements ImageStore{
	public final Logger logger = LoggerFactory.getLogger(S3Storage.class);
	
	private final AmazonS3 Client;
	
	public S3Storage(){
		logger.info("Setting up S3 Client.");
		Client = new AmazonS3Client();
	}
	
	
	public void put(String key, byte[] value){
		ObjectMetadata imageMetaData = new ObjectMetadata();
		InputStream inputstream = null;
		try{
		inputstream = new ByteArrayInputStream(value);
		} catch(Exception e){
		logger.debug("Failed to get byte data");
		logger.debug(e.getMessage());
		}
		
		try{
		Client.putObject("richmedia.rovicorp.com",key,inputstream,imageMetaData);
		} catch(Exception e){
			logger.info(e.getMessage());
		}
	}

}
