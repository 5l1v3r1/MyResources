package com.rovicorp.rabbitmq_s3_push.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rabbitmq.client.ConnectionFactory;
import com.rovicorp.rabbitmq_s3_push.helperclasses.ImageHandler;
import com.rovicorp.rabbitmq_s3_push.helperclasses.RMQimageListener;
import com.rovicorp.rabbitmq_s3_push.helperclasses.S3Storage;
import com.rovicorp.rabbitmq_s3_push.interfaces.Handler;
import com.rovicorp.rabbitmq_s3_push.interfaces.ImageListener;
import com.rovicorp.rabbitmq_s3_push.interfaces.ImageStore;

public class ImageUploadApplication {
	
	public static void main(String[] args){ 

	ConnectionFactory factory = new ConnectionFactory();
	ImageStore s3Storage	= new S3Storage();
	Handler connectionhandler	= new ImageHandler();
	connectionhandler.setstore(s3Storage);
	ImageListener listener = new RMQimageListener(factory);
	listener.setHandler(connectionhandler);
	
		
	
	while(true){}
	
	}

}
