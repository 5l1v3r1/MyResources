package utils;

/**
 *
 * @author josephappeah
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetImageMetadata {
	
	private static final File file = new File("/Users/josephappeah/Desktop/coding/git_repo/myresources/Lance_Application_1/src/java/utils/config.properties");
	//private static final Logger logger = LoggerFactory.getLogger(GetImageMetadata.class);
	private static final Properties props = new Properties();

	
	public static void main(String[] args){
		GetImageMetadata p = new GetImageMetadata();

			System.out.println(p.executeImageSearchResult("boy"));
			//System.out.println(p.executeComputerVision(new File("src/main/resources/image.png")));
	}
	
	
	public static ArrayList<String> executeImageSearchResult(String queryparam) {
		//logger.debug("loading properties from {}.",file.getPath());
            try {
                props.load(new FileInputStream(file));
                //props.forEach((key,value) -> logger.info("{}:{}",key,value));
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(GetImageMetadata.class.getName()).log(Level.SEVERE, null, ex);
            }
		ArrayList<String> imageURLs = new ArrayList<String>();
                String responseJSON = null;
		InputStream requestStream   = null;
		HttpURLConnection connection = null;
		
		//split the bing url by the delimiter and add the query parameter
		//logger.debug("getting bing api url components.");
		String[] bingURLComponents = props.get("image_search_url").toString().split("QUERYPARAMGOESHERE");
		
		//logger.debug("forming request url ");
		String requestURL = bingURLComponents[0]+ "\""+ queryparam+"\""+ bingURLComponents[1];
		//logger.debug(requestURL);
		URL url = null;
            try {
                url = new URL(requestURL);
            } catch (MalformedURLException ex) {
                java.util.logging.Logger.getLogger(GetImageMetadata.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		//setting up request
		try{
			//logger.debug("setting up httpurlconnection.");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Ocp-Apim-Subscription-Key", (String) props.get("image_search_key"));

		} catch(Exception e){
			//logger.debug("failed to set up httpurlconnection.");
			//logger.debug(e.getMessage());
		}
		
		try{
			//logger.debug("getting image URLs from Bing.");
			connection.setRequestMethod("GET");
			requestStream = connection.getInputStream();
			
			//logger.debug("storing response data");
			responseJSON = IOUtils.toString(requestStream);//.replace("\\/","/").replace("\\\"", "\"");
                        
                        try{
                        //logger.debug("parsing the input to json");
                        JSONArray value = (JSONArray) new JSONObject(responseJSON).get("value");
                        
                        for(int count = 0; count < value.length();count++){
                            JSONObject thumbnail = (JSONObject) value.get(count);
                            imageURLs.add((String) thumbnail.get("thumbnailUrl"));
                        }
                        } catch (Exception e){
                        //logger.debug("failed to get image urls from json");
                        //logger.debug("e.getMessage()");
                        }

                        
			
			
		} catch(Exception e){
			//logger.debug("failed to get image URLs from Bing.");
			//logger.debug(e.getMessage());
		}
		
		return imageURLs;
	}



	public static String executeComputerVision(File image) {
		HttpURLConnection connection = null;
		URL url = null;
            try {
                url = new URL((String) props.get("computer_vision_url"));
            } catch (MalformedURLException ex) {
                java.util.logging.Logger.getLogger(GetImageMetadata.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		String imageMetadata = "", requestlines  = null;
		InputStream requestStream   = null;

		
		//setting up request
		try{
			//logger.debug("setting up httpurlconnection.");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Ocp-Apim-Subscription-Key", (String) props.get("computer_vision_key"));
			connection.setRequestProperty("Content-type", "application/octet-stream");
			connection.setDoOutput(true);
			connection.setUseCaches(false);

		} catch(Exception e){
			//logger.debug("failed to set up httpurlconnection.");
			//logger.debug(e.getMessage());
		}
		
		DataOutputStream postRequest = null;
		
		try{
			//logger.debug("creating post request.");
			connection.setRequestMethod("POST");
			postRequest = new DataOutputStream(connection.getOutputStream());
			byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(image));
			postRequest.write(imageBytes);
		}catch(Exception e){
			//logger.debug("failed to create post request.");
			//logger.debug(e.getMessage());
		}
		
		try{
			//logger.debug("sending post request.");		
			postRequest.flush();
			postRequest.close();
		}catch(Exception e){
			//logger.debug("failed to send post request.");
			//logger.debug(e.getMessage());
		}
		
		try{
			//logger.debug("getting image metadata.");		
			requestStream = connection.getInputStream();
			//logger.debug("storing response data");
			BufferedReader r = new BufferedReader(new InputStreamReader(requestStream));
			while (((requestlines  = r.readLine()) != null)) {
				imageMetadata+=requestlines;
			}
			
			
		} catch(Exception e){
			//logger.debug("failed to get image URLs from Bing.");
			//logger.debug(e.getMessage());
		}
		
		return imageMetadata;
	}

	
	

}
