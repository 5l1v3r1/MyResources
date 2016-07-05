package com.josephappeah.corporate.js_email_client.interfaces;

import java.io.File;

public interface EmailClientHandler{
	public void setProperties(String sender,String password, String recepient, File attachment, String host, String message,String subject);
	public void processRequest() throws Exception;
	public void sendEmail() throws Exception;
}
