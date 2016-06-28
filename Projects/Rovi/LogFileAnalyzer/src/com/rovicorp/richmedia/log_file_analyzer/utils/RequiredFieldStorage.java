
package com.rovicorp.richmedia.log_file_analyzer.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jappeah
 */
public class RequiredFieldStorage {
    public Map<String,Map> requiredfields = new HashMap<String,Map>();
    public void store(String ipaddress, Integer bytelength){
        
        Map<String,Integer> ip_metadata = new HashMap<String,Integer>();
        
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
            e.printStackTrace();
        }
    }
}
