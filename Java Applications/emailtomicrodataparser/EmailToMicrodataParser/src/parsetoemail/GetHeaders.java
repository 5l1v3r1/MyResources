package parsetoemail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import utils.emailParserInterface;


public class GetHeaders implements emailParserInterface{   
        @Override
        public Map get(File file) throws Exception{
            
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        char c1 = 0xA;
        String headers = "";
        String MessageHeaders ="";

        //convert contents of text file to a string object
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text).append(c1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //convert String Buffer to a regular string
        //newlines still identical to origional message
        String converted = contents.toString();

        try {
            //create internet headers object
            InternetHeaders emailVar = new InternetHeaders();

            //convert string to input stream
            InputStream streamVar = new ByteArrayInputStream(converted.getBytes("US-ASCII"));

            //load input stream into internet headers object
            emailVar.load(streamVar);

            //print all lines
            Enumeration num = emailVar.getAllHeaderLines();
            while(num.hasMoreElements()){
                //The rfc822 email header parser library
                //adds the hex character "0D" to headers that contain the character "0A"
                //The character "0D" must be removed
                //System.out.print(num.nextElement().toString().replaceAll("\r", ""));
                MessageHeaders += ((num.nextElement().toString().replaceAll("\r", "")) + "\n").toString();
                //System.out.print(c1);
                
                
                
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        String[] Strings = MessageHeaders.split("\n");
        Map map = new HashMap();
        for(String string: Strings){
            ArrayList<String>  list = new ArrayList<String>();
            for(String innerString : string.split(" ")){
                    list.add(innerString);
            }
            map.put(string.split(" ")[0],list);
        }
        
        for(Object key: map.keySet()){
             //System.out.println(key.toString());
            if(key.toString().toLowerCase().contains("from")||key.toString().toLowerCase().contains("to")){
                ArrayList<String>  list = new ArrayList<String>();
                list = (ArrayList<String>) map.get(key);
                //System.out.println(list.get(0)+": "+list.get(1)+" "+list.get(2));
                
            }
        }
        
        //System.out.println(map);
        return map;
    }//end main method    
}
