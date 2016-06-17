package generate_rm.compare.image_json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

/**
 *
 * @author jappeah
 */
public class Generate_rmCompareImage_json {
	Map<String,Object> CF_URLs = new HashMap<>();//Contains <number++ , CF_MetaData>
	Map<String,Object> CPS_URLs = new HashMap<>();//Contains <number++ , CPS_MetaData>
        private static boolean isComparingImages = false;
        private static File urlfile = null;
	Map<String,Object> List = new HashMap<>(); //Contains<CF/CPS, CF_URLs/CPS_URLs>
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inputFileLocation = null;
        String action            = null;
        try{
            inputFileLocation = args[0];
            action            = args[1];
        }catch (Exception e){
            e.printStackTrace();
        }
        if("true".equals(action)){isComparingImages = true;}
        urlfile = new File(inputFileLocation);
        
        Generate_rmCompareImage_json grmj = new Generate_rmCompareImage_json();
        
        System.out.println(action + "  "+ inputFileLocation);
        try {
            grmj.generate(urlfile);
            grmj.storeMetadata();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void  generate(File file) throws Exception{
		int count = 0;
		String eachLine;
		InputStream in = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
		
        try {
			while((eachLine = reader.readLine())!= null){
                                String 	cps_staticURL;
                                String 	cloudfrontURL;
                                
				if(isComparingImages == true){
                                    cps_staticURL 		= "http://cps-static.rovicorp.com/2"+ eachLine;
                                    cloudfrontURL		= "http://d7cks5tzzz8un.cloudfront.net/2"+ eachLine;
                                }else{
                                    cps_staticURL               = eachLine;
                                    cloudfrontURL               = eachLine;
                                }
				
				Map<String,String> CF_MetaData = new HashMap<>();// Contains<URL and Number Of Bytes>
				Map<String,String> CPS_MetaData = new HashMap<>();// Contains<URL and Number Of Bytes>
				
				CF_MetaData.put("URL", cloudfrontURL);
				CF_MetaData.put("Bytes", Integer.toString(GetImageByteSize.get(cloudfrontURL)));
				
				CPS_MetaData.put("URL", cps_staticURL);
				CPS_MetaData.put("Bytes", Integer.toString(GetImageByteSize.get(cps_staticURL)));
				
				CF_URLs.put(Integer.toString(count), CF_MetaData);
				CPS_URLs.put(Integer.toString(count), CPS_MetaData);
				
				count++;
			}
		} catch (Exception e) {
			
			
		}
        
		reader.close();
		
	}
	
	
	public void storeMetadata(){
		JSONObject json = new JSONObject();

		json.put("result", get());
		File file = null;
                
                try{
                    file = new File(urlfile.getParent()+"/new_json_data.txt");
                }catch (Exception e){
                    e.printStackTrace();
                }
                
                
		try{
		FileUtils.writeStringToFile(file, json.toString());
		} catch (Exception e){
                    e.printStackTrace();
		}
	}
	
	
	public Map<String,Object> get(){
		List.put("CF", CF_URLs);
		List.put("CPS", CPS_URLs);
		return List;
	}

    
}
