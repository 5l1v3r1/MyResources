package com.rovicorp.compare_rm_images_service.resources;
import com.rovicorp.compare_rm_images_service.utils.DependencyHandler;
import java.util.Properties;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.undertow.Undertow;

import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import java.io.IOException;
import javax.servlet.ServletException;

public class CompareRmImagesApplicationServer {
	
final static Logger logger = LoggerFactory.getLogger(CompareRmImagesApplicationServer.class);
	static Properties props = new Properties();
	private static Integer portnumber = null;
	UndertowJaxrsServer server = null;
    
	public CompareRmImagesApplicationServer(DependencyHandler di){
		portnumber = Integer.parseInt(di.getPortNumber());
	}
	
	
    public void startUp() throws IOException, ServletException{
    	logger.debug("Starting up server");
    	Undertow.Builder builder = Undertow.builder()
    			.addHttpListener(portnumber, "0.0.0.0");
    	
    	UndertowJaxrsServer server = new UndertowJaxrsServer();
    	// Deploy JAX-RS application
    	server.deploy(CompareRmImagesApplication.class);
        //server.addPath("/", resource(new ClassPathResourceManager(Thread.currentThread().getContextClassLoader())).setWelcomeFiles("index.html"));
    	//Deploy static resource handler
    	DeploymentInfo servletBuilder = Servlets.deployment()
    		.setClassLoader(Thread.currentThread().getContextClassLoader())
    	        .setContextPath("/")
    	        .setDeploymentName("comapre-rm-images-homepage")
    	        .setResourceManager(new ClassPathResourceManager(Thread.currentThread().getContextClassLoader()))
    	        .addWelcomePage("index.html").addWelcomePage("qa_test_page.html");
        
        
        
    	server.deploy(servletBuilder);
    	
    	server.start(builder);
    	logger.debug("Listening on port:{}",portnumber);
    }
    
    public void shutDown(){
    	try{
    		logger.debug("Shutting down server");
    		server.stop();
    	} catch(Exception e){
    		logger.debug("Failed to shut down server",e);
    	}
    	
    }


}