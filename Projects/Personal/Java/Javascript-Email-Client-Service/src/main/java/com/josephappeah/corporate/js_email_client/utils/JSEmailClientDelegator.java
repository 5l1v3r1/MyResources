package com.josephappeah.corporate.js_email_client.utils;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.js_email_client.interfaces.EmailClientHandler;

public class JSEmailClientDelegator {
	private String sender = null;
	private String recepient = null;
	private File attachment = null;
	private String host = null;
	private String message = null;
	private String password = null;
	private String subject = null;
	
	private static Map<String,EmailClientHandler> emailclienthandlers = null;
	private static EmailClientHandler ech = null;
	private static final Logger logger = LoggerFactory.getLogger(JSEmailClientDelegator.class);

	private void setProperties(String sender,String password, String recepient, File attachment, String host, String message, String subject){
		String filename = null;
		this.sender = sender;
		this.recepient = recepient;
		this.host = host;
		this.message = message;
		this.subject = subject;
		this.password = password;
		this.attachment = attachment;
		if(attachment != null){
			filename = attachment.getName();
		}else{
			filename = "No attachment";
		}
		
		logger.debug("Request properties set successfully:{}; {}; {}; {}; {};",sender, recepient, host , filename ,message);
	}
	
	private void selectEmailHandler() throws Exception{
		try{
			logger.debug("Selecting appropriate handler.");
			if(sender.contains("@gmail")){
				JSEmailClientDelegator.ech = emailclienthandlers.get("gmail");
				logger.debug("gmail client handler selected.");
			}
			else if(sender.contains("@yahoo")    || sender.contains("@ymail")){
				JSEmailClientDelegator.ech = emailclienthandlers.get("ymail");
				logger.debug("yahoo mail client handler selected.");
			}
			else if(sender.contains("@hotmail")  || sender.contains("@live")){
				JSEmailClientDelegator.ech = emailclienthandlers.get("hotmail");
				logger.debug("windows/live client handler selected.");
			}
			else if(this.host!= null){
				JSEmailClientDelegator.ech = emailclienthandlers.get("misc");
				logger.debug("unknown host. miscellaneous client handler selected.");
			}
			else if(
					!sender.contains("@gmail")   || 
					!sender.contains("@gmail")   ||
					!sender.contains("@yahoo")   || !sender.contains("@ymail") ||
					!sender.contains("@hotmail") || !sender.contains("@live")  &&
					this.host == null)
			{
				logger.error("Failed to find an appropriate handler and no host provided by user.");
				throw new Exception();
			}
		}catch(Exception e){
			logger.error("Failed to find appropriate handler.",e);
			throw e;
		}
	}
	
	private void sendEmail() throws Exception {
		logger.debug("Processing email request.");
		try{
			logger.debug("Setting properties for EmailClientHandler class {}.", ech.getClass().getName());
			JSEmailClientDelegator.ech.setProperties(sender,password, recepient, attachment, host, message, subject);
		}catch(Exception e){
			logger.error("Failed to set properties.",e);
			throw e;
		}
		
		try{
			//logger.debug("Initializing email request processor.");
			JSEmailClientDelegator.ech.processRequest();
		}catch(Exception e){
			logger.error("Failed to initialize email request processor.",e);
			throw e;
		}
		
		try{
			logger.debug("Initializing email send action.");
			JSEmailClientDelegator.ech.sendEmail();
		}catch(Exception e){
			logger.error("Failed to initialize email send action.",e);
			throw e;
		}
	}
	
	public void delegate(String sender,String password, String recepient, File attachment, String host, String message, String subject) throws Exception{
		logger.debug("Initializing application delegate.");
		setProperties(sender,password, recepient, attachment, host, message,subject);
		try{
			selectEmailHandler();
		}catch (Exception e){
			logger.error("Failed to initialize applcation delegate.",e);
			throw e;
		}
		sendEmail();
		logger.debug("Email sent successfully!");
	}
	
	public static void setEMailClientHandlers(Map<String,EmailClientHandler> emailclienthandlers){
		logger.debug("Obtaining email client handlers.");
		JSEmailClientDelegator.emailclienthandlers = emailclienthandlers;
		logger.debug("Email client handlers Successfully obtained.");
	}
}
