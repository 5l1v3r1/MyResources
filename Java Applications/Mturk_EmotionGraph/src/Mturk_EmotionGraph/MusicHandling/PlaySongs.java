/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mturk_EmotionGraph.MusicHandling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author josephappeah
 */
public class PlaySongs {     //Plays a random song from a list
    
    ArrayList<String> Song = new ArrayList<String>();  
        Random rand = new Random(); 
        String CurrentSong = "paul"; 
       
        AudioInputStream audioInputStream;
        Clip clip;

        public PlaySongs() throws Exception {
            
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
            reload();

        }
       
        public void reload() throws Exception{
            int number = rand.nextInt(Song.size());
            this.clip = AudioSystem.getClip();
            CurrentSong = Song.get(number).toString().substring(Song.get(number).toString().lastIndexOf("/"),Song.get(number).toString().lastIndexOf("."));
            this.audioInputStream = AudioSystem.getAudioInputStream(new File(Song.get(number)).getAbsoluteFile());
            //System.out.print(Song.get(number));
            clip.open(audioInputStream);
            
        }
            
        public void start(){
            
        clip.start();
        }
        
        public void stop(){
        clip.stop();
        }
        //CurrentSong = FilePaths[number];

    }
    

