package com.rovicorp.compare_rm_images.applicationserver;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.undertow.Undertow;
import io.undertow.Undertow.Builder;

public class server {
	
final static Logger logger = LoggerFactory.getLogger(server.class);

    public static void main(String[] args) {
    	int port = 8082;
    	Builder builder = Undertow.builder()
    			.addHttpListener(port, "0.0.0.0");
    	UndertowJaxrsServer server = new UndertowJaxrsServer();
    	try {
    		logger.debug("Initializing Rest Service");
	    	server.start(builder);
	    	server.deploy(CompareRmImagesApplication.class);
	    	logger.info("Listening on {}",port);
    	} catch (RuntimeException e) {
    		server.stop();
    	}
    }
}