package com.rovicorp.compare_rm_images_service.application;

import com.rovicorp.compare_rm_images_service.resources.CompareRmImagesApplicationServer;
import com.rovicorp.compare_rm_images_service.services.ReturnRandomImageData;
import com.rovicorp.compare_rm_images_service.services.ReturnRecentImageData;
import com.rovicorp.compare_rm_images_service.services.ReturnSpecificImageData;
import com.rovicorp.compare_rm_images_service.utils.RequestProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CompareRmImagesApp{

	private static final Logger logger = LoggerFactory.getLogger(CompareRmImagesApp.class);

	
	public static void main(String[] args){
            
                
		logger.debug("Enter main:");
		
		
		ClassPathXmlApplicationContext ctx = null;
		try{
			logger.debug("Initializing Application");
			ctx = new ClassPathXmlApplicationContext("CompareRmImages.xml");
		} catch (Exception e){
			logger.debug("Initializing Error: {}", e.getMessage(), e);
			
		}
		
		try{
			RequestProcessor 			rp 			= (RequestProcessor) ctx.getBean("RequestProcessor");
                        
                        
			ReturnRandomImageData 		rrandomid 	= (ReturnRandomImageData) ctx.getBean("RandomRequest");
			rrandomid.getRestRequestProcessor(rp);
			ReturnRecentImageData 		rrecentid 	= (ReturnRecentImageData) ctx.getBean("RecentRequest");
			rrecentid.getRestRequestProcessor(rp);
			ReturnSpecificImageData 	rsid 		= (ReturnSpecificImageData) ctx.getBean("SpecificRequest");
			rsid.getRestRequestProcessor(rp);
		}catch(Exception e){
			logger.error("Failed to Initialize Application",e);
		}
		
		try{
			logger.debug("Launching server");
			CompareRmImagesApplicationServer cri_server = (CompareRmImagesApplicationServer) ctx.getBean("ApplicationServer");
			cri_server.startUp();
		} catch(Exception e){
			logger.debug("Failed to launch server {}",e.getMessage(),e);
		}
	}


}
