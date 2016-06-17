package generate_rm.compare.image_json;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;


public class GetImageByteSize {
	/*
	public static void main(String[] args) throws Exception{
		GetByteNumber b = new GetByteNumber();
		System.out.println(b.get("http://d7cks5tzzz8un.cloudfront.net/2/Open/Hulu_1693/Program/5097206/_derived_jpg_q90_75x75_m0/hulu0c6ddc3d0-a3c1-4c18-8ab9-6e57ae6f5d9a.jpg"));
		
	}*/
	
	public static int get(String urlString) throws Exception{
		URLConnection connection = null;
		InputStream in = null;
		URL url = new URL(urlString);
		try{
		 connection = url.openConnection();
		} catch(Exception e){

		}
		try{
		 in = connection.getInputStream();
		} catch(Exception e){
                    
		}
		byte[] Byte = IOUtils.toByteArray(in);
		return Byte.length;
	}

}
