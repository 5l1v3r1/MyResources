package com.rovicorp.compare_rm_images_service.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rovicorp.compare_rm_images_service.interfaces.RestRequestProcessor;
import com.rovicorp.compare_rm_images_service.utils.RequestProcessor;

@Path("/specific")
public class ReturnSpecificImageData{

	private static RestRequestProcessor  	rrp 			= null;
	private String					responsedata	= null;
    private static final Logger 	logger 			= LoggerFactory.getLogger(ReturnSpecificImageData.class);
    
	@GET
	@Produces("application/json")
	public Response returnSpecificData(@QueryParam("id") Integer id, @QueryParam("env") String environment, @QueryParam("isqa") boolean istulsa){
		logger.debug("Specific image request recieved for image id:{}",id);
		try{
			responsedata = rrp.specificImageRequest(environment,istulsa,id);
		}catch(Exception e){
                    logger.debug("",e);
			return Response.status(404).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
		}
		
		return Response.status(200).entity(responsedata).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
	
    public void getRestRequestProcessor(RequestProcessor rp){
    	rrp = rp;
    }

}
