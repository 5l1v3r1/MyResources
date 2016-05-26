package com.rovicorp.compare_rm_images.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/get")
public class Get {

	public final Logger logger = LoggerFactory.getLogger(Get.class);
	
@GET
@Produces("application/json")
public Response get() throws Exception{
	String FileUrl = "src/main/resources/imageMetadata.txt";
	String eachLine = null;
	String JSONOutput = "";
	
	InputStream in = null;
	try{
	in = new FileInputStream(new File(FileUrl));
	}catch(Exception e){
		logger.info("Failed to get file from {}",FileUrl);
		logger.debug(e.getMessage());
	}
	
	BufferedReader reader = null;
	try{
	reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
	}catch(Exception e){
		logger.info("Failed to read file");
	}
	
	
	while((eachLine = reader.readLine()) != null){
		JSONOutput += eachLine;
	}
	
	return Response.status(200).entity(JSONOutput).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
}

}
