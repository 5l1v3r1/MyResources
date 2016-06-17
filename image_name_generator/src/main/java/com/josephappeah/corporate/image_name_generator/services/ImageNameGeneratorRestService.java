package com.josephappeah.corporate.image_name_generator.services;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.josephappeah.corporate.image_name_generator.interfaces.RestRequestDelegator;
import com.josephappeah.corporate.image_name_generator.interfaces.ServiceRequestLayer;

@Path("/image_name_generator")
public class ImageNameGeneratorRestService implements ServiceRequestLayer{
	private static RestRequestDelegator ingsd = null;
	
	@GET
	@Produces("text/plain")
	public Response getImageName(InputStream In){
		try{
			ingsd.setRequestParameters(In);
			ingsd.executeRequest();
		}catch(Exception e){
			return Response.status(404).entity("The Requested Resource was not found, or service "
					+ "is temporarily down. Please try again in a few hours. My sincerest"
					+ " apologies for any inconvenience created.").build();
		}
		
		try{
			return Response.status(200).entity(ingsd.generateResponse()).build();
		}catch(Exception e){
			return Response.status(404).entity("The Requested Resource was not found, or service "
					+ "is temporarily down. Please try again in a few hours. My sincerest"
					+ " apologies for any inconvenience created.").build();
		}
	}

	public void setRestRequestDelegate(RestRequestDelegator rrd) {
		ImageNameGeneratorRestService.ingsd = rrd;
	}
}
