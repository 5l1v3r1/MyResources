package com.josephappeah.corporate.js_email_client.service;

import java.io.File;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.js_email_client.utils.JSEmailClientDelegator;

@Path("/emailclient")
public class JSEmailClientService {
	private static final Logger logger = LoggerFactory.getLogger(JSEmailClientService.class);
	private File attachment = null;
	@POST
	public Response sendEmail(
		@QueryParam("sender") String sender,
		@QueryParam("password") String password,
		@QueryParam("subject") String subject,
		@QueryParam("recepient") String recepient,
		@QueryParam("message") String message,
		@QueryParam("host") String host,
		byte[] attachment
			){
		logger.debug("New request recieved.");
		
		try{
			if(attachment.length != 0){
				logger.debug("Converting bytes to file.");
				FileUtils.writeByteArrayToFile(this.attachment, attachment);
			}else{
				attachment = null;
			}
		}catch(Exception e){
			logger.error("Failed to convert bytes to file",e);
			return Response
					.status(500)
					.entity("Resource unavailable.")
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.build();
		}
		
		JSEmailClientDelegator jecd = new JSEmailClientDelegator();
		
		try{
			logger.debug("Processing request");
			jecd.delegate(sender,password, recepient, this.attachment, host,message, subject);
			return Response
					.status(200)
					.entity("Email successfully sent to "+recepient+".")
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.build();
			
		}catch(Exception e){
			logger.error("Failed to Process Request.",e);
			return Response
					.status(500)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.entity("Resource unavailable.")
					.build();
		}
	}
}
