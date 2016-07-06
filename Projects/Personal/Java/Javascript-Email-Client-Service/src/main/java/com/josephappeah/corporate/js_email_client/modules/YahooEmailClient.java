package com.josephappeah.corporate.js_email_client.modules;

import java.io.File;

import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.js_email_client.interfaces.EmailClientHandler;
import com.josephappeah.corporate.js_email_client.utils.EmailRequestProcessor;

public class YahooEmailClient implements EmailClientHandler{
	private String sender = null;
	private String recepient = null;
	private String host = "smtp.mail.yahoo.com";
	private String message = null;
	private File attachment = null;
	private String subject  = null;
	private String password = null;
	
	private MimeMessage email = null;
	private static final Logger logger = LoggerFactory.getLogger(YahooEmailClient.class);
	public void setProperties(String sender, String password, String recepient, File attachment, String host, String message, String subject) {
		logger.debug("Obtaining properties");
		this.sender = sender;
		this.recepient = recepient;
		this.attachment = attachment;
		this.password = password;
		this.subject = subject;
		
		if(message == null && attachment == null){
			this.message = "No message or attahment were detected for this email.";
		}else if(message == null && attachment != null){
			this.message = "This email has an attahment.";
		}else if (message != null){
			this.message = message;
		}
	}

	public void processRequest() throws Exception{
		try{
			logger.debug("Initializing request processor.");
			email = EmailRequestProcessor.processRequest(sender, password, recepient, attachment, host, message, subject, email);
		}catch(Exception e){
			logger.error("Failed to initialize request processor.",e);
		}
	}
	
	public void sendEmail() throws Exception {
		try{
			logger.debug("Attempting to send email.");
			Transport.send(email);
		}catch(Exception e){
			logger.error("Failed to Send email.",e);
			throw e;
		}
	}
}
