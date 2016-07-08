package com.josephappeah.corporate.js_email_client.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.js_email_client.utils.JSEmailClientDelegator;
import com.josephappeah.corporate.js_email_client.utils.MultipartFormParamParser;



@Path("/js-email-client")
public class JSEmailClientService {
	
	private static final Logger logger = LoggerFactory.getLogger(JSEmailClientService.class);
	private File attachment = null;
	private Map<String,Object> params = new HashMap<String,Object>();
	
	@POST
	@GET
	@OPTIONS
	@Consumes(MediaType.MULTIPART_FORM_DATA)	
	public Response sendEmail(MultipartFormDataInput properties){
		logger.debug("{}",properties);
		logger.debug("New request recieved.");
		params = MultipartFormParamParser.getParams(properties);
		
		String sender = params.get("sender").toString();
		String password = params.get("password").toString();
		String subject = params.get("subject").toString();
		String recepient = params.get("recepient").toString();
		String message = params.get("message").toString();
		String host = params.get("host").toString();
		File attachment = null;
		if(params.get("attachment")!= null){
			attachment = (File) params.get("attachment");
		}
		
		try{
			if(attachment != null && attachment.length() <= 25000){
				logger.debug("Converting bytes to file.");
				this.attachment = attachment;
			}else{
				this.attachment = null;
			}
		}catch(Exception e){
			logger.error("Failed to convert bytes to file",e);
			return Response
					.status(500)
					.entity("Your e-mail attachment could not be processed. Please ensure the file is not corrupted or over 25MB.")
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT")
					.build();
		}
		
		JSEmailClientDelegator jecd = new JSEmailClientDelegator();
		
		try{
			logger.debug("Processing request");
			jecd.delegate(sender,password, recepient, this.attachment, host,message, subject);
			return Response
					.status(200)
					.entity("Your e-mail successfully sent to "+recepient+".")
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT")
					.build();
			
		}catch(Exception e){
			logger.error("Failed to Process Request.",e);
			return Response
					.status(500)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT")
					.entity("Resource unavailable.")
					.build();
		}
		
	}
}
