package utils;
/**
 *
 * @author jappeah
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
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class GetImageMetadata{
	
	private static final File file = new File("../config.properties");
	//private static final Logger logger = LoggerFactory.getLogger(GetImageMetadata.class);
	private static final Properties props = new Properties();

	
	public static void main(String[] args){
		GetImageMetadata gim = new GetImageMetadata();

			System.out.println(gim.executeImageSearchResult("boy"));
			//System.out.println(gim.executeComputerVision(new File("src/main/resources/image.png")));

	}
	
	
	public static String executeImageSearchResult(String queryparam){
		//logger.debug("loading properties from {}.",file.getPath());
                try {
                    props.load(new FileInputStream(file));
                    //props.forEach((key,value) -> logger.info("{}:{}",key,value));
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(GetImageMetadata.class.getName()).log(Level.SEVERE, null, ex);
                }
		
		String imageURLs= "",requestlines  = null;
		InputStream requestStream   = null;
		HttpURLConnection connection = null;
                StringBuilder responseBuild = new StringBuilder();
		
		//split the bing url by the delimiter and add the query parameter
		//logger.debug("getting bing api url components.");
		String[] bingURLComponents = "https://bingapis.azure-api.net/api/v5/images/search?q=QUERYPARAMGOESHERE&count=1&offset=0&mkt=en-us&safeSearch=Moderate".split("QUERYPARAMGOESHERE") ;//props.get("image_search_url").toString().split("QUERYPARAMGOESHERE");
		
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
			connection.setRequestProperty("Ocp-Apim-Subscription-Key","3d9c8e796bf2453198b88bbf6375ffe6"); //(String) props.get("image_search_key"));

		} catch(Exception e){
			//logger.debug("failed to set up httpurlconnection.");
			//logger.debug(e.getMessage());
		}
		
		try{
			//logger.debug("getting image URLs from Bing.");
			connection.setRequestMethod("GET");
			requestStream = connection.getInputStream();
			
			//logger.debug("storing response data");
			BufferedReader br = new BufferedReader(new InputStreamReader(requestStream));
                        imageURLs = IOUtils.toString(br);              
			/*while (((requestlines  = br.readLine()) != null)) {
				responseBuild.append(requestlines);
			}*/

			
		} catch(Exception e){
			//logger.debug("failed to get image URLs from Bing.");
			//logger.debug(e.getMessage());
		}
                System.out.print(imageURLs.replace("\\/","/").replace("\\\"", ""));
		return imageURLs.replace("\\/","/").replace("\\\"", "");
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
		
		return imageMetadata.replace("\\/","/").replace("\\\"", "");
	}
	
	
	

}
