package com.rovicorp.rabbitmq_s3_push.helperclasses;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rovicorp.rabbitmq_s3_push.interfaces.Handler;
import com.rovicorp.rabbitmq_s3_push.interfaces.ImageStore;

public class ImageHandler implements Handler{
	public final Logger logger = LoggerFactory.getLogger(ImageHandler.class); 
	private ImageStore store;
	
	public void callback(Map<String, Object> map, byte[] body) {
		try{
		store.put((String)map.get("key"),body);
		} catch(Exception e){
			logger.debug("Failed to pass key: {} to storage", (String)map.get("key"));
			logger.debug(e.getMessage());
		}
	}

	public void setstore(ImageStore store) {
		this.store = store;
	}

}
