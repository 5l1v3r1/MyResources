package com.josephappeah.corporate.js_email_client.application;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.josephappeah.corporate.js_email_client.interfaces.EmailClientHandler;
import com.josephappeah.corporate.js_email_client.modules.GmailEmailClient;
import com.josephappeah.corporate.js_email_client.modules.HotmailEmailClient;
import com.josephappeah.corporate.js_email_client.modules.MiscEmailClient;
import com.josephappeah.corporate.js_email_client.modules.YahooEmailClient;
import com.josephappeah.corporate.js_email_client.resources.JSEmailClientServer;
import com.josephappeah.corporate.js_email_client.utils.JSEmailClientDelegator;

public class JSEmailClientApp {
	private static final Logger logger = LoggerFactory.getLogger(JSEmailClientApp.class);
	private static Map<String,EmailClientHandler> emailclienthandlers = new HashMap<String,EmailClientHandler>();
	private static GmailEmailClient gec = null;
	private static YahooEmailClient yec = null;
	private static HotmailEmailClient hec = null;
	private static MiscEmailClient mec = null;
	private static JSEmailClientServer jsecs = null;
	private static Properties props = new Properties();
	
	public static void main(String[] args) throws Exception{
		
		logger.debug("Enter Main:");
		ClassPathXmlApplicationContext ctx = null;
		try{
			logger.debug("Initialzing application.");
			ctx =	new ClassPathXmlApplicationContext("JSEmailClient.xml");
		}catch(Exception e){
			logger.error("Failed to initialize application",e);
			throw e;
		}
		
		try{
			logger.debug("Obtaining properties");
			props.load(new FileInputStream(new File(System.getenv("ROVI_BASE") +File.separator +"JSEmailClientProperties.properties")));
		}catch(Exception e){
			logger.error("Failed to obtain properties.",e);
			throw e;
		}
		
		try{
			logger.debug("Creating beans.");
			gec = (GmailEmailClient) ctx.getBean("GmailClient");
			yec = (YahooEmailClient) ctx.getBean("YahooClient");
			hec = (HotmailEmailClient) ctx.getBean("HotmailClient");
			mec = (MiscEmailClient) ctx.getBean("ProvidedClient");
			jsecs = (JSEmailClientServer) ctx.getBean("ApplicationServer");
			logger.debug("Beans created successfully.");
		}catch(Exception e){
			logger.error("Failed to create beans.");
			throw e;
		}
		
		emailclienthandlers.put("gmail", gec);
		emailclienthandlers.put("ymail", yec);
		emailclienthandlers.put("hotmail", hec);
		emailclienthandlers.put("misc", mec);
		
		try{
			logger.debug("Setting email client handlers.");
			JSEmailClientDelegator.setEMailClientHandlers(emailclienthandlers);
		}catch(Exception e){
			logger.error("Failed email client handlers.",e);
			throw e;
		}
		
		try{
			logger.debug("Starting server.");
			JSEmailClientServer.portnumber = Integer.parseInt((String) props.getProperty("portnumber"));
			jsecs.startUp();
		}catch(Exception e){
			logger.error("Failed to start server.",e);
			throw e;
		}
		
	}
}
