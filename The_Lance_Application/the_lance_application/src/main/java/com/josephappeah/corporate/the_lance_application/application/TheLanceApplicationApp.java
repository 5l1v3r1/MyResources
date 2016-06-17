package com.josephappeah.corporate.the_lance_application.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.josephappeah.corporate.the_lance_application.resources.TheLanceApplicationServer;


public class TheLanceApplicationApp {
	private static final Logger logger = LoggerFactory.getLogger(TheLanceApplicationApp.class);

	public static void main(String[] args){
		
		ClassPathXmlApplicationContext 		ctx 		= null;
		
		try{
			logger.debug("Gathering required resources.");
			ctx = new ClassPathXmlApplicationContext("TheLanceApplication.xml");
		} catch (Exception e){
			logger.debug("Failed to gather resources. Initialization error: {}", e.getMessage(), e);
		}
		
		
		logger.debug("\n\n\n");
		logger.debug("               -------------------------------               ");
		logger.debug("               Launching The Lance Application               ");
		logger.debug("               -------------------------------               ");
		logger.debug("\n\n\n");
		
		
		try{
			logger.debug("Starting server from main.");
			TheLanceApplicationServer tlas = (TheLanceApplicationServer) ctx.getBean("lanceapplicationserver");
			tlas.startUp();
		}catch (Exception e){
			logger.debug("Failed to start server from main.",e);
		}
			

	}
}
