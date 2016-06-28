/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mturk_EmotionGraph.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author josephappeah
 */
public class variables {
    public Map Data = new HashMap();
    public Map Songs = new HashMap();
    public Random rand = new Random(); 
    public int StartSong = 0;
    public String CurrentSong; 
    public String FirstName;
    public String LastName;
    public String UserNumber;
    public String[] FilePaths;
    public int Count;
    public ArrayList<String> Song = new ArrayList<String>(); 
    
    public void setVariables(Map data, Map songs,int startsong, 
            String currentsong, String firstname, String lastname, 
            String[] filepaths, int count, ArrayList<String> song){
        
        if(data == null){Data = Data;} else{Data = data;}
        if(songs == null){Songs = Songs;} else{Songs = songs;}
        if(startsong == 0){StartSong = StartSong;} else{StartSong =startsong;}
        if(currentsong == null){CurrentSong = CurrentSong;} else{CurrentSong = currentsong;}
        if(firstname == null){FirstName = FirstName;} else{FirstName = firstname;}
        if(lastname == null){LastName = LastName;} else{LastName = lastname;}
        if(filepaths == null){FilePaths = FilePaths;} else{FilePaths = filepaths;}
        if(count == 0){Count = Count;} else{Count = count;}
        if(song == null){Song = Song;} else{Song = song;}
    
    }
    
}
