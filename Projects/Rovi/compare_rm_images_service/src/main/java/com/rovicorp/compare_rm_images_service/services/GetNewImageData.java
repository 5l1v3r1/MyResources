package com.rovicorp.compare_rm_images_service.services;

import java.io.File;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Path("/put")
public class GetNewImageData {
	
	final static Logger logger = LoggerFactory.getLogger(GetNewImageData.class);
	
	@POST
	public Response getfile(String in){
		boolean isFileRenamed = false;

		try{
			File original_metadata_file = new File("original_imageMetadata.txt");
			new File("imageMetadata.txt").renameTo(original_metadata_file);
			isFileRenamed = true;
		} catch(Exception e){
			logger.debug("Failed to rename old image metadata file",e);
		}
		
                
		try{
			if(isFileRenamed){
                            FileUtils.writeStringToFile(new File("imageMetadata.txt"), in);
			}
		} catch(Exception e){
			logger.debug("Failed to store InputStream",e);
			return Response.status(404).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
		}
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();

	}

}
