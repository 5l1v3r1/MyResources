package fileuploadtest;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.apache.commons.io.FileUtils;

import com.rovicorp.rabbitmq_s3_push.interfaces.ImageStore;

public class FileStorage implements ImageStore{

	public void put(String key, byte[] value) {
			File file = new File("src/main/resources/imagedata.txt");
			ByteArrayOutputStream a = new ByteArrayOutputStream();
			try{
			a.write(value);
			a.write(key.getBytes());
			} catch(Exception e){
				
			}
			
			
		try{
			
			FileUtils.writeByteArrayToFile(file, a.toByteArray());
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
