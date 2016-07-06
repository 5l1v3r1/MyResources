package com.josephappeah.corporate.js_email_client.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.js_email_client.utils.JSEmailClientDelegator;
import com.josephappeah.corporate.js_email_client.utils.RestServiceParamParser;

@Path("/emailclient")
public class JSEmailClientService {
	private static final Logger logger = LoggerFactory.getLogger(JSEmailClientService.class);
	private File attachment = null;
	private Map<String,Object> params = new HashMap<String,Object>();
	
	@POST
	public Response sendEmail(String properties){
		logger.debug("New request recieved.");
		params = RestServiceParamParser.getParams(new JSONObject(properties));
		
		String sender = params.get("sender").toString();
		String password = params.get("password").toString();
		String subject = params.get("subject").toString();
		String recepient = params.get("recepient").toString();
		String message = params.get("message").toString();
		String host = params.get("host").toString();
		byte[] attachment = null;
		
		try{
			logger.debug("Obtaining email attachment.");
			attachment = IOUtils.toByteArray(IOUtils.toInputStream(params.get("attachment").toString()));
		} catch(Exception e){
			logger.error("Failed to obtain attachment",e);
			return Response
					.status(500)
					.entity("Your e-mail attachment could not be processed. Please ensure the file is not corrupted or over 25MB.")
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT")
					.build();
		}
		
		
		try{
			if(attachment.length != 0 && attachment.length <= 25000){
				logger.debug("Converting bytes to file.");
				this.attachment = File.createTempFile("tempfile", "");
				FileUtils.writeByteArrayToFile(this.attachment, attachment);
			}else{
				attachment = null;
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
