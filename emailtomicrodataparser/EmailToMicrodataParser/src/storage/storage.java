/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import org.apache.commons.io.FileUtils;
import utils.storageInterface;

public class storage implements storageInterface{

    Map CurrentEmailDataBase = new HashMap();
    
    public Map get() throws IOException, MessagingException, Exception{
        return CurrentEmailDataBase;
    }   

    public void flush() throws Exception{
        FileUtils.writeStringToFile(new File(System.getProperty("user.home")+ "/Desktop/Parsed_Email.txt"), "", "UTF-8");
    }
 
    @Override
    public void put(Map<String,Object> map) throws Exception {
        CurrentEmailDataBase.clear();
        for( Map.Entry<String,Object> entry: map.entrySet()){
            CurrentEmailDataBase.put(entry.getKey(), entry.getValue());
        }
    }

    public File storeEmailInTempFile(File file) throws IOException{
    //Store Entire file in Temporary file
    String line = null;
    File TempByteStorage = File.createTempFile("fileName",".txt");
    StringBuffer ObtainedFileToString = new StringBuffer();

    FileUtils.writeStringToFile(TempByteStorage, ObtainedFileToString.toString());
    return TempByteStorage;
    }
}
