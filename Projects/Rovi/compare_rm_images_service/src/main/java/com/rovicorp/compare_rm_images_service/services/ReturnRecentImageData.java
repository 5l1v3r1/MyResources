package com.rovicorp.compare_rm_images_service.services;

import com.rovicorp.compare_rm_images_service.interfaces.RestRequestProcessor;
import com.rovicorp.compare_rm_images_service.utils.RequestProcessor;

import javax.ws.rs.core.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/recent")
public class ReturnRecentImageData{
    
	private static RestRequestProcessor  	rrp 			= null;
	private String					responsedata	= null;
	private static final Logger 	logger 			= LoggerFactory.getLogger(ReturnRecentImageData.class);

    @GET
    @Produces("application/json")
	public Response returnData(@QueryParam("env") String environment) {
		logger.debug("Recent image request recieved");
		
		
		try{
			responsedata = rrp.recentImageRequest(environment,false);
		}catch(Exception e){
                                        logger.debug("",e);
			return Response.status(404).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
		}
		
		return Response.status(200).entity(responsedata).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
  
    
    public void getRestRequestProcessor(RequestProcessor rp){
    	this.rrp = rp;
    }


}
