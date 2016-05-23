package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;

import parsetoemail.GetBody;
import parsetoemail.GetHeaders;
import parsetoemail.mimeParser;
import storage.storage;
import emailtomicrodata.parseToMicrodata;

public class EmailToMicrodataParser {
    
public EmailToMicrodataParser(){
    //Get the Action
    //Get The Email Type
}
public Map          messageBody         = new HashMap();
public Map          messageHeaders      = new HashMap();
public Map          mimeParsedMessage   = new HashMap();

GetBody             bodyOnly            = new GetBody();
GetHeaders          headersOnly         = new GetHeaders(); 
mimeParser          mimeParsedEmail     = new mimeParser();
storage             store               = new storage();
parseToMicrodata    Markup              = new parseToMicrodata();

File TheFile = new File("c:/Users/jappeah/Desktop/THY CO-OP STUFF-ETH/samples/messages/m0001.txt");

    public static void main(String[] args) throws FileNotFoundException, IOException, MessagingException, Exception{
        EmailToMicrodataParser Parse = new EmailToMicrodataParser();   
        Parse.ParseEmail();
        Parse.storeEmailData();
        //System.out.println(Parse.getMicroDataMarkup("EmailMessage","Action"));
    }
    
    public void ParseEmail() throws Exception{
        messageBody             = bodyOnly.get(TheFile);
        messageHeaders          = headersOnly.get(TheFile);
        mimeParsedMessage       = mimeParsedEmail.get(TheFile);
    } 
    
    public void storeEmailData() throws Exception{
        store.put(messageBody);
        store.put(messageHeaders);
        store.put(mimeParsedMessage);
    }
    
    public String getMicroDataMarkup(String MessageType, String Action) throws Exception{
        return Markup.parserDelegator(MessageType,Action,store.get());
    }
    
    
}

/*
TO-DO
1.CREATE MANUAL PARSER FOR THE GETHEADER METHOD

*/