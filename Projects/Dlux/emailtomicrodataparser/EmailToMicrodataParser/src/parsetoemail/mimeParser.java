/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsetoemail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.util.MimeMessageParser;
import utils.emailParserInterface;

/**
 *
 * @author jappeah
 */
public class mimeParser implements emailParserInterface{
    
    @Override
    public Map get(File file) throws Exception {
        
        Map<String,Object> map = new HashMap(); 
        map.put("MimeFrom", parseAllInformation(file).getFrom());
        map.put("MimeTo", parseAllInformation(file).getTo());
        map.put("MimeSubject", parseAllInformation(file).getSubject());
        map.put("MimeHTML", parseAllInformation(file).getHtmlContent());
        map.put("MimeDescription", parseAllInformation(file).getMimeMessage().getDescription());
        map.put("MimeEcoding", parseAllInformation(file).getMimeMessage().getEncoding());
        //System.out.print(parseAllInformation(file).getMimeMessage());
        return map;
    }
    
        public MimeMessageParser parseAllInformation(File Email) throws FileNotFoundException, IOException, MessagingException, Exception{
        //Declaration of Variables
        String line = null;
        StringBuffer file = new StringBuffer();
        InputStream input = new FileInputStream(Email); 
        //Storing the email in file
        try (BufferedReader read = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
        //Storing the email in file
            while((line = read.readLine())!= null){
                file.append(line+"\n");
            }
        }
        
       //Creating a MimeMessage from the email
        Session session = Session.getDefaultInstance(new Properties());
        byte[] b = String.valueOf(file).getBytes();
        
        
        InputStream ist = new ByteArrayInputStream(b);
        MimeMessage message = new MimeMessage(session,ist);
        
        //MimeMessage email = new MimeMessage(null,input);
        MimeMessageParser Parser = new MimeMessageParser(message);
        //System.out.println(message.getFileName());
        
        try
        {
            //System.out.println(message.getSubject()); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return Parser;
    }


    
}
