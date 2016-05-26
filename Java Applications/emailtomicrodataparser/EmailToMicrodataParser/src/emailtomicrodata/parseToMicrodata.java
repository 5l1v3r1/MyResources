
package emailtomicrodata;

import java.util.Map;

    public class parseToMicrodata{
        String EmailMessage;
        public String parserDelegator(String MessageType,String Action, Map EmailContents){
            String Message =  "Invalid Message Type.";
            if( "EmailMessage".equals(MessageType)){Message = parsePlainEmail(EmailContents);}
            
            return Message;
        }
        
//THIS IS WHERE I INTEND TO WRITE OUT THE ACTUAL MICRODATA MARKUPS FOR THE TYPES OFFERED
        public String parsePlainEmail(Map EmailContents){
            EmailMessage =""
                    + "<div itemscope itemtype=\"http://schema.org/EmailMessage\">\n"
                    +   "<div itemprop=\"potentialAction\" itemscope itemtype=\"http://schema.org/ReviewAction\">\n"
                   // +       "<div itemprop=\"Review\" itemscope itemtype=\"http://schema.org/Review\">"
                    +       "<span itemprop=\"name\">"+EmailContents.get("MimeFrom")+"</span>\n"
                   // +       "<span itemprop=\"about\">"+EmailContents.get("messageSubject")+"</span>"
                    +       "<span itemprop=\"description\">"+EmailContents.get("MimeSubject").toString().replace("\n", "")+"<span>\n"
                    +   "</div>\n"
                    + "</div>\n"
                    
                    ;
            //System.out.print(EmailMessage);
            return EmailMessage;
            
        }
        public void parseArticle(Map EmailContents){}
        public void parseOrder(Map EmailContents){}
        public void parsePromotion(Map EmailContents){}
        public void parseReservation(Map EmailContents){}
    
    }