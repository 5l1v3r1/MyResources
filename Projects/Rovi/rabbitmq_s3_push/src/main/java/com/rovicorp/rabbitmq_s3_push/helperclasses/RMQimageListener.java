package com.rovicorp.rabbitmq_s3_push.helperclasses;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rovicorp.rabbitmq_s3_push.interfaces.Handler;
import com.rovicorp.rabbitmq_s3_push.interfaces.ImageListener;

public class RMQimageListener implements ImageListener{
	private ConnectionFactory factory;
	public final Logger logger = LoggerFactory.getLogger(RMQimageListener.class);
	
	public RMQimageListener(ConnectionFactory factory){
		this.factory = factory;
	}
	
	public void setHandler(final Handler handle){
		Connection connection = null;
		try{
		connection 	= 	factory.newConnection();
		} catch(Exception e){
			logger.debug("Failed to setup connection.");
			logger.debug(e.getMessage());
		}
		
		Channel channel = null;
		try{
		channel    	= 	connection.createChannel();
		} catch(Exception e){
			logger.debug("Failed to create channel.");
			logger.debug(e.getMessage());
		}
		
		
		try{
		channel.queueDeclare("queue", false, false, false, null);
		} catch(Exception e){
			logger.debug("Failed to setup queue.");
			logger.debug(e.getMessage());
		}
				
		
		Consumer consumer = new DefaultConsumer(channel) {
		@Override   
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,  byte[] body) throws IOException{
			
			try{
			handle.callback(properties.getHeaders(), body);
			} catch(Exception e){
				logger.debug("Failed to pass data to handler.");
			}
			}
		};
		
		try{
		channel.basicConsume("queue", true, consumer);
		} catch(Exception e){
			logger.debug("Failed to consume data");
		}

}
	
}
