
package com.rovicorp.compare_rm_images_service.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.time.StopWatch;

@Path("/getsize")
public class GetImageSize {
    
    private static final Logger logger = LoggerFactory.getLogger(GetImageSize.class);
    
    @GET
    @Produces("text/plain")
    public Response response (@QueryParam("url") String filepath) throws IOException{
                StopWatch stopwatch = new StopWatch();
                float loadTime = 0;
		HttpURLConnection connection = null;
		InputStream in = null;
                URL url = null;
                
                try{
		url = new URL(filepath);
                }catch (Exception e){
                    logger.debug("Failed to get url",e);
                }
		try{
		 connection = (HttpURLConnection) url.openConnection();
                 connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                 connection.setRequestProperty("Accept","*/*");
                 connection.setDoInput(true);
                 connection.setDoOutput(true);
		} catch(Exception e){
			logger.debug("Failed to setup url connection", e);
		}
		try{
                 
                stopwatch.start();
		 in = connection.getInputStream();
                stopwatch.stop();
                loadTime = stopwatch.getTime();
                stopwatch.reset();
                 
		} catch(Exception e){
                 
			logger.debug("Failed to get input stream", e);
		}
                byte[] Byte = null;
                try{
                     Byte = IOUtils.toByteArray(in);
                     in.close();
                     connection.disconnect();
                } catch(Exception e){
                    return Response.status(200).entity("null").build();
                }
                
	
        
        return Response.status(200).entity(Byte.length+"///"+loadTime).build();
    }
    
}
