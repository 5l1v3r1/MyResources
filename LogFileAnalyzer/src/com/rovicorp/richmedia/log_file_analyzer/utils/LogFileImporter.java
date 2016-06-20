
package com.rovicorp.richmedia.log_file_analyzer.utils;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author jappeah
 */
public class LogFileImporter {
    /*
        1.  comb through provided directory and obtain all .log file names.
        2.  i.   pull each file, 
            ii.  call the file parser to obtain required fields, 
            iii. and store the fields in the file storage class.
    */
    
    private static ArrayList<String> logfileurls = new ArrayList<String>();
    
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
    
    
}
