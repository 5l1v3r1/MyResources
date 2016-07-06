package com.josephappeah.corporate.js_email_client.utils;

import java.io.File;
import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailRequestProcessor {
	private static final Logger logger = LoggerFactory.getLogger(EmailRequestProcessor.class);
	
	public static MimeMessage processRequest(
			final String sender, 
			final String password, 
			String recepient, 
			File attachment, 
			String host, 
			String message, 
			String subject,
			MimeMessage email) throws Exception
	{
		logger.debug("Setting properties for host.");
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender,password);
					}
				});
		
		if(message == null && attachment != null){
			logger.debug("No message found. Sending attachment message only.");
			try{
				BodyPart emailattachment = new MimeBodyPart();
				emailattachment.setText("This is message body");
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(emailattachment);
				emailattachment = new MimeBodyPart();
				DataSource source = new FileDataSource(attachment);
				emailattachment.setDataHandler(new DataHandler(source));
				emailattachment.setFileName(attachment.getName());
				multipart.addBodyPart(emailattachment);
				email.setContent(multipart);
			}catch(Exception e){
				logger.error("Failed to set email properties.",e);
				throw e;
			}
	         
		}else if(message != null && attachment == null){
			logger.debug("No attachement found. Sending email message only.");
			try{
				logger.debug("Setting email properties.");
				email = new MimeMessage(session);
				email.setFrom(new InternetAddress(sender));
				email.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recepient));
				email.setSubject(subject);
				email.setText(message);
			}catch(Exception e){
				logger.error("Failed to set email properties.",e);
				throw e;
			}
			
		}else if(message != null && attachment != null){
			logger.debug("Both attachement and message found.");
			try{
				BodyPart emailattachment = new MimeBodyPart();
				emailattachment.setText("This is message body");
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(emailattachment);
				emailattachment = new MimeBodyPart();
				DataSource source = new FileDataSource(attachment);
				emailattachment.setDataHandler(new DataHandler(source));
				emailattachment.setFileName(attachment.getName());
				multipart.addBodyPart(emailattachment);
				email.setContent(multipart);
			}catch(Exception e){
				logger.error("",e);
				throw e;
			}
			
		}else if(message == null && attachment == null){
			logger.debug("No attachement or message found.");
			try{
				logger.debug("Setting email properties.");
				email = new MimeMessage(session);
				email.setFrom(new InternetAddress(sender));
				email.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recepient));
				email.setSubject(subject);
				email.setText(message);
			}catch(Exception e){
				logger.error("Failed to set email properties.",e);
				throw e;
			}
		}
		
		return email;
	}
}
