package com.rovicorp.richmedia.log_file_analyzer.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author jappeah
 */
public class LogFileParser {
    RequiredFieldStorage rfs = new RequiredFieldStorage();
    public void parse(ArrayList<String> logfileurls){
        for (String filepath : logfileurls){
            File file = new File(filepath);
            int count = 0;
            System.out.println(filepath);
            LineIterator iter = null;
            try{
                iter = FileUtils.lineIterator(file);
            }catch(Exception e){
                e.printStackTrace();
            }
            
            try {
                int httpStartPoint = 0; //the http request field contains spaces\
                int httpStopPoint = 0;
                int counter = 0;
                String line = "";
                while (iter.hasNext()) {
                    String eachline = iter.nextLine();
                    String[] fields = eachline.split(" ");
                    
                    for(int i = 0; i < fields.length ; i++){
                        if(fields[i].startsWith("\"")){
                            httpStartPoint = counter;
                        }
                        if(fields[i].endsWith("\"")){
                            httpStopPoint = counter;
                            break;
                        }
                        counter++;
                    }
                    int byteindex = 7 + (httpStopPoint - httpStartPoint); 
                    rfs.store(fields[0],Integer.parseInt(fields[byteindex]));
                    //if(count%100 == 0){System.out.println(count);}
                    count++;
                    counter = 0;
                }
            }finally{
                LineIterator.closeQuietly(iter);
            }
        }
        

    }
    
    public void flush(String FilePath){
        File File = new File(FilePath);
        
        try{
            FileUtils.writeStringToFile(File,"IP Address,Frequency,Total Number Of Bytes \n",true);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        for(Map.Entry<String,Map> entry : rfs.requiredfields.entrySet()){
            String eachline = entry.getKey()+","+entry.getValue().get("frequency")+","+entry.getValue().get("totalbytes");
            try{
            FileUtils.writeStringToFile(File,eachline+"\n",true);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void flushentry(String FilePath, String Content){
                try{
            FileUtils.writeStringToFile(new File(FilePath),Content+" \n",true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
