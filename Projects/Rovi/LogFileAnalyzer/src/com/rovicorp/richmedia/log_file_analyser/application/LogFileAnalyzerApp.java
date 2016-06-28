package com.rovicorp.richmedia.log_file_analyser.application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author jappeah
 */
public class LogFileAnalyzerApp {
    private static ArrayList<String> FileDirectory = new ArrayList<>();
    private static String filed = null;
    private static final ArrayList<String> logfileurls = new ArrayList<>();
    
    public static void main(String[] args) throws Exception{

            //FileDirectory.add("c:/users/jappeah/desktop/14-06-16/");
            //FileDirectory.add("c:/users/jappeah/desktop/15-06-16/");
            //FileDirectory.add("c:/users/jappeah/desktop/16-06-16/");
            //FileDirectory.add("c:/users/jappeah/desktop/18-06-16/");
            //FileDirectory.add("c:/users/jappeah/desktop/19-06-16/");

        
        for(String file : FileDirectory){
            LogFileAnalyzerApp lfaa = new LogFileAnalyzerApp();
            filed = file;
            lfaa.parseLine(lfaa.getLogFiles(file));
        }
    }
    
    
    //log file importer
    public ArrayList<String> getLogFiles(String logfiledirectory){
        File folder = new File(logfiledirectory);
        
	File[] fileNames = folder.listFiles();
        
        for (File fileName : fileNames) {
            if ("log".equals(FilenameUtils.getExtension(fileName.getAbsolutePath()))) {
                logfileurls.add(fileName.getAbsolutePath());
            }
        }
        
        return logfileurls;
    }
        
        
    public Map<String,Map> requiredfields = new HashMap<>();
    public void store(String ipaddress, Integer bytelength){
        
        Map<String,Integer> ip_metadata = new HashMap<>();
        
        try{
            
            if(requiredfields.containsKey(ipaddress)){
                
                int ipfrequency = (int) requiredfields.get(ipaddress).get("frequency") + 1;
                int ipbytestotal= (int) requiredfields.get(ipaddress).get("totalbytes") + bytelength;
                requiredfields.get(ipaddress).put("frequency",ipfrequency);
                requiredfields.get(ipaddress).put("totalbytes",ipbytestotal);

            }else{
                ip_metadata.put("frequency",1);
                ip_metadata.put("totalbytes",bytelength);
                requiredfields.put(ipaddress,ip_metadata);
            }
            
        }catch(Exception e){
        }
    }
    
    
    
    public void parseLine(ArrayList<String> logfileurls){
        for (String filepath : logfileurls){
            //get each line from file
            File file = new File(filepath);
            System.out.println(filepath);
            LineIterator iter = null;
            try{
                iter = FileUtils.lineIterator(file);
            }catch(Exception e){
            }

            try {
                //obtain each field from line
                while (iter.hasNext()) {
                    ArrayList<String> eachLinesFields = new ArrayList<>();
                    String eachline = iter.nextLine();
                    String[] fields = eachline.split(" ");
                    String string   = "";
                    
                    for(int i = 0; i < fields.length ; i++){
                        string = fields[i];
                        if(fields[i].startsWith("\"")){
                            string="";
                            for(int j = i; j <fields.length;j++){
                                string+=" "+ fields[j];    
                                if(fields[j].endsWith("\"")){
                                    i=j;
                                    break;
                                }
                            }
                        }else if(fields[i].startsWith("[")){
                            string="";
                            for(int j = i; j <fields.length;j++){
                                string+=" "+fields[j];    
                                if(fields[j].endsWith("]")){
                                    i=j;
                                    break;
                                }
                            }
                        }
                    eachLinesFields.add(string);
                    }
                    //set handlers 
                    settopRefPerResponseStatus(eachLinesFields);
                    //setbytesperstatus(eachLinesFields);
                    //settopIPPerResponseStatus(eachLinesFields);
                    //setTopUrlsForHighUsers(topuserips(),eachLinesFields);
                    
                    //System.out.print(eachLinesFields+"\n");
                    eachLinesFields.clear();
                }
            }finally{
                LineIterator.closeQuietly(iter);
            }
        }
        //set data exporters
        gettopRefPerResponseStatus();
        //gettopIPPerResponseStatus();
        //getbytesperstatus();
        //getTopUrlsForHighUsers();
    }
    
 
    
   //==================| top ip per response status & the bandwidth |===========//
    Map<String,Map<String,ArrayList>> rspnsstatus = new HashMap<>();
    public void gettopIPPerResponseStatus(){
        String filepath = null;
        File file = null;        
        for(Map.Entry<String, Map<String, ArrayList>> entry : rspnsstatus.entrySet()){ 
            for(Map.Entry<String,ArrayList> inner_entry : entry.getValue().entrySet()){
                    filepath = filed+"_Top_IP_Status_"+entry.getKey()+".csv";
                    file = new File(filepath);
                String eachline = entry.getKey() +"," +inner_entry.getKey() +","+ inner_entry.getValue().get(0)+","+inner_entry.getValue().get(1) ;
                try{
                    FileUtils.writeStringToFile(file,eachline+"\n",true);
                }catch(Exception e){
                }
            }
        }
    }

    public void settopIPPerResponseStatus(ArrayList<String> LineData){          
        Map<String,ArrayList> ip = new HashMap<>();
        try{
            if("200".equals(LineData.get(5))){  
            if(rspnsstatus.containsKey(LineData.get(5))){
                //if the status code exists        
                if(rspnsstatus.get(LineData.get(5)).containsKey(LineData.get(0))){
                    //then if the ip address exists
                    ArrayList<Long> ip_metadata = new ArrayList<>();
                    ip_metadata.add((long)rspnsstatus.get(LineData.get(5)).get(LineData.get(0)).get(0)+ 1);
                    ip_metadata.add((long) rspnsstatus.get(LineData.get(5)).get(LineData.get(0)).get(1) + Integer.parseInt(LineData.get(6)));                 
                    rspnsstatus.get(LineData.get(5)).put(LineData.get(0),ip_metadata);
                }else{
                    ArrayList<Long> ip_metadata = new ArrayList<>();
                    ip_metadata.add((long)1);
                    ip_metadata.add(Long.parseLong(LineData.get(6)));
                    rspnsstatus.get(LineData.get(5)).put(LineData.get(0),ip_metadata);
                }
            }else{
                ArrayList<Long> ip_metadata = new ArrayList<>();
                ip_metadata.add((long)1);
                ip_metadata.add(Long.parseLong(LineData.get(6)));
                ip.put(LineData.get(0),ip_metadata);
                rspnsstatus.put(LineData.get(5),ip);
            }
            }
        }catch(Exception e){
        }
    }
    //==========================================================================//
    
    
    
    //=====================| urls requested by top ips |========================//
    Map<String,Map<String,Long>> topipbyurlcount = new HashMap<>();
    public void setTopUrlsForHighUsers(ArrayList<String> ipaddresslist,ArrayList<String> Linedata){
        //if ip is high usage
        if(ipaddresslist.contains(Linedata.get(0))){
            //if the coun map conatins the ip
            if(topipbyurlcount.containsKey(Linedata.get(0))){
                //if the ip contains the url
                if(topipbyurlcount.get(Linedata.get(0)).containsKey(Linedata.get(4))){
                    long urlfrequency = topipbyurlcount.get(Linedata.get(0)).get(Linedata.get(4)) + (long)1;
                    topipbyurlcount.get(Linedata.get(0)).put(Linedata.get(4), urlfrequency);
                }else{
                    topipbyurlcount.get(Linedata.get(0)).put(Linedata.get(4),(long)1);
                }
            }else{
                Map<String,Long> urlcount = new HashMap<>();
                urlcount.put(Linedata.get(4),(long)1);
                topipbyurlcount.put(Linedata.get(0),urlcount);
            }
        }
    }
    public void getTopUrlsForHighUsers(){
        String filepath = null;
        File file = null;        
        for(Map.Entry <String, Map<String,Long>> entry : topipbyurlcount.entrySet()){ 
            for(Map.Entry<String,Long> inner_entry : entry.getValue().entrySet()){
                    filepath = filed+"_URL_Count_"+entry.getKey()+".csv";
                    file = new File(filepath);
                String eachline = entry.getKey() +"," +inner_entry.getKey().replace(" ", "").replace(",","") +","+ inner_entry.getValue() ;
                try{
                    FileUtils.writeStringToFile(file,eachline+"\n",true);
                }catch(Exception e){
                }
            }
        }
    }
    public ArrayList<String> topuserips(){
        ArrayList<String> topuserips = new ArrayList<>();
        topuserips.add("144.198.28.10");
        topuserips.add("69.241.25.56");
        topuserips.add("207.46.13.13");
        topuserips.add("207.46.13.128");
        return topuserips;
    }
    //==========================================================================//
    
    
    
    //=========================|Bytes Per Status Code|========================================//
    
    Map<String,Long> bytesperstatus = new HashMap<>();
    
    public void setbytesperstatus(ArrayList<String> LineData){
        
        if(bytesperstatus.containsKey(LineData.get(5))){
                Long bytecount = bytesperstatus.get(LineData.get(5)) +Long.parseLong(LineData.get(6));
                bytesperstatus.put(LineData.get(5),bytecount);
            }else{
                bytesperstatus.put(LineData.get(5),Long.parseLong(LineData.get(6)));
        }
    }
    
    public void getbytesperstatus(){
        String filepath = filed+"bytesperStatus.csv";
        File file = new File(filepath);
        Long total = null;
        for(Map.Entry <String, Long> entry : bytesperstatus.entrySet()){ 
               // total += entry.getValue();
                String eachline = entry.getKey() +"," +entry.getValue() ;
                try{
                    FileUtils.writeStringToFile(file,eachline+"\n",true);
                }catch(Exception e){
                } 
        }
                try{
                    FileUtils.writeStringToFile(file,"total,"+total+"\n",true);
                }catch(Exception e){
                } 
    }
    
    
    
//========================= details by referrer ================================
     Map<String,Map<String,ArrayList>> refrspnsstatus = new HashMap<>();
    public void gettopRefPerResponseStatus(){
        String filepath = null;
        File file = null;        
        for(Map.Entry<String, Map<String, ArrayList>> entry : refrspnsstatus.entrySet()){ 
            for(Map.Entry<String,ArrayList> inner_entry : entry.getValue().entrySet()){
                    filepath = filed+"_Top_Ref_Status_"+entry.getKey()+".csv";
                    file = new File(filepath);
                String eachline = entry.getKey() +"," +inner_entry.getKey() +","+ inner_entry.getValue().get(0)+","+inner_entry.getValue().get(1) ;
                try{
                    FileUtils.writeStringToFile(file,eachline+"\n",true);
                }catch(Exception e){
                }
            }
        }
    }

    public void settopRefPerResponseStatus(ArrayList<String> LineData){    
        String ref = null;
        try{
            ref = LineData.get(7).split("/")[2].replaceAll("\"", "");
        }catch(Exception e){
            ref = LineData.get(7).replaceAll("\"", "");
        }
        
        Map<String,ArrayList> ip = new HashMap<>();
        try{
            if("200".equals(LineData.get(5))){  
            if(refrspnsstatus.containsKey(LineData.get(5))){
                //if the status code exists        
                if(refrspnsstatus.get(LineData.get(5)).containsKey(ref)){
                    //then if the ip address exists
                    ArrayList<Long> ip_metadata = new ArrayList<>();
                    ip_metadata.add((long)refrspnsstatus.get(LineData.get(5)).get(ref).get(0)+ 1);
                    ip_metadata.add((long) refrspnsstatus.get(LineData.get(5)).get(ref).get(1) + Integer.parseInt(LineData.get(6)));                 
                    refrspnsstatus.get(LineData.get(5)).put(ref,ip_metadata);
                }else{
                    ArrayList<Long> ip_metadata = new ArrayList<>();
                    ip_metadata.add((long)1);
                    ip_metadata.add(Long.parseLong(LineData.get(6)));
                    refrspnsstatus.get(LineData.get(5)).put(ref,ip_metadata);
                }
            }else{
                ArrayList<Long> ip_metadata = new ArrayList<>();
                ip_metadata.add((long)1);
                ip_metadata.add(Long.parseLong(LineData.get(6)));
                ip.put(ref,ip_metadata);
                refrspnsstatus.put(LineData.get(5),ip);
            }
            }
        }catch(Exception e){
        }
    }   
    
    
    //====================Top ip per referrer===================================
    Map<String,Map<String,Long>> topipbyrefcount = new HashMap<>();
    public void setTopIpForHighRefs(ArrayList<String> reflist,ArrayList<String> Linedata){
        String ref = null;
        try{
            ref = Linedata.get(7).split("/")[2].replaceAll("\"", "");
        }catch(Exception e){
            ref = Linedata.get(7).replaceAll("\"", "");
        }
        
        
        //if ip is high usage
        if(reflist.contains(ref)){
            //if the coun map conatins the ip
            if(topipbyrefcount.containsKey(ref)){
                //if the ip contains the url
                if(topipbyrefcount.get(ref).containsKey(Linedata.get(4))){
                    long urlfrequency = topipbyrefcount.get(ref).get(Linedata.get(4)) + (long)1;
                    topipbyrefcount.get(ref).put(Linedata.get(4), urlfrequency);
                }else{
                    topipbyrefcount.get(ref).put(Linedata.get(4),(long)1);
                }
            }else{
                Map<String,Long> urlcount = new HashMap<>();
                urlcount.put(Linedata.get(4),(long)1);
                topipbyrefcount.put(ref,urlcount);
            }
        }
    }
    public void getTopIpForHighRefs(){
        String filepath = null;
        File file = null;        
        for(Map.Entry <String, Map<String,Long>> entry : topipbyrefcount.entrySet()){ 
            for(Map.Entry<String,Long> inner_entry : entry.getValue().entrySet()){
                    filepath = filed+"_IP_Count_"+entry.getKey()+".csv";
          
                }
            }
        }
    }
    public ArrayList<String> toprefs(){
        ArrayList<String> topuserips = new ArrayList<>();
        topuserips.add("144.198.28.10");
        topuserips.add("69.241.25.56");
        topuserips.add("207.46.13.13");
        topuserips.add("207.46.13.128");
        return topuserips;
    }
}


