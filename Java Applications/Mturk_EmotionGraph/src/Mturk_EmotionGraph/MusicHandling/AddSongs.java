/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mturk_EmotionGraph.MusicHandling;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author josephappeah
 */
public class AddSongs {
     ArrayList<String> Song = new ArrayList<String>();  
    
        public void AddSongs(){
    //Obtain the current directory of the jar file
       String Directory=  System.getProperty("user.dir");
    
    //Obtain all .wav files in the directory
        int ifWAV = 0;
        File[] filesInDirectory = new File(Directory).listFiles();
        for(File f : filesInDirectory)
        {
        String filePath = f.getAbsolutePath();
        String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
        
        if("wav".equals(fileExtenstion))
        {
        ifWAV++;
        Song.add(filePath);
        }
        } 
        
        
        
       
    }
    
}
