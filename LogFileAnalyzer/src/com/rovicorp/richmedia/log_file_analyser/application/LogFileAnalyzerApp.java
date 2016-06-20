package com.rovicorp.richmedia.log_file_analyser.application;

import com.rovicorp.richmedia.log_file_analyzer.utils.LogFileImporter;
import com.rovicorp.richmedia.log_file_analyzer.utils.LogFileParser;
import java.util.ArrayList;

/**
 *
 * @author jappeah
 */
public class LogFileAnalyzerApp {
    private static String FileDirectory = null;
    private static ArrayList<String> logfileurls = new ArrayList<String>();
    
    public static void main(String[] args) throws Exception{
        try{
            FileDirectory = "c:/users/jappeah/desktop/15-06-16";
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        
        LogFileImporter lfi = new LogFileImporter();
        logfileurls = lfi.getLogFiles(FileDirectory);
        
        LogFileParser lfp = new LogFileParser();
        lfp.parse(logfileurls);
        
        lfp.flush("c:/users/jappeah/desktop/limelightata-15-06-16.csv");
    }
    
}
