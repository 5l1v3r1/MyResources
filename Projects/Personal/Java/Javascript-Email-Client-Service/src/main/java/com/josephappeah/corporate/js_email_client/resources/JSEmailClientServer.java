package com.josephappeah.corporate.js_email_client.resources;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;

public class JSEmailClientServer {
	private static final Logger logger = LoggerFactory.getLogger(JSEmailClientServer.class);
	public static Integer		portnumber		= null;
	UndertowJaxrsServer 		server 			= new UndertowJaxrsServer();
	
	public void startUp(){
		try{
			logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			logger.debug("+        Starting up JS Email Client Application Server.        +");
			logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
	    	Undertow.Builder 	builder 		= Undertow.builder().addHttpListener(portnumber, "0.0.0.0");
	    	
	    	logger.debug("Creating homepage servlet.");
	    	DeploymentInfo 		servletbuilder 	= Servlets.deployment()
	        		.setClassLoader(Thread.currentThread().getContextClassLoader())
	        	    .setContextPath("/")
	        	    .setDeploymentName("JS-Email-Client-Application-Homepage")
	        	    .setResourceManager(new ClassPathResourceManager(Thread.currentThread().getContextClassLoader()))
	        	    .addWelcomePage("index.html");
	    	
	    	logger.debug("Starting resource and servlet deployment.");
	    	server.deploy(JSEmailClientApplication.class);
	    	server.deploy(servletbuilder);
	    	
	    	logger.debug("Deploying Server.");
	    	server.start(builder);
	    	logger.debug("Deployment Complete. Listening on port:{}",portnumber);
	    	
		}catch(Exception e){
			logger.debug("Failed to start up JS Email Client Application undertow server",e);
		}
	}
	
	public void shutDown(){
		try{
			logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
			logger.debug("+ Shutting down JS Email Client Application server. +");
			logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			server.stop();
			
		}catch(Exception e){
			logger.debug("Failed to shut down JS Email Client Application undertow server");
		}
	}
}
