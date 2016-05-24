/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mturk_EmotionGraph.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class StoreSpreadsheetData implements SpreadsheetInterface {
    getEmotion e = new getEmotion();
    Map Data = e.getData();

    
    
    
     public void StoreData(String FileName) throws Exception{
        String S = System.getProperty("user.name");
        Workbook workbook1 = Workbook.getWorkbook(new File("/Users/"+ S +"/Desktop/"+FileName+".xls"));
        Sheet sheet1 = workbook1.getSheet(0);
         int rows = sheet1.getRows();
         int columns = sheet1.getColumns();
         String[][] StorageMatrix =  new String[columns][rows] ;
         
         // System.out.println( + rows);
         // System.out.println( + columns);
            for (int x = 0; x<rows; x++)
            {
               Cell UserNumber_ = sheet1.getCell(0,x); 
               Cell FirstName_ = sheet1.getCell(1,x);
               Cell LastName_ = sheet1.getCell(2,x);
               Cell Song = sheet1.getCell(3,x);
               Cell Time = sheet1.getCell(4,x);
               Cell Xcoord = sheet1.getCell(5,x); 
               Cell Ycoord = sheet1.getCell(6,x);
               Cell Emotion = sheet1.getCell(7,x);
               Cell Current_Date = sheet1.getCell(8,x);
               Cell Current_Time = sheet1.getCell(9,x);
               StorageMatrix[0][x] = UserNumber_.getContents();
               StorageMatrix[1][x] = FirstName_.getContents();
               StorageMatrix[2][x] = LastName_.getContents();   
               StorageMatrix[3][x] = Song.getContents();
               StorageMatrix[4][x] = Time.getContents();
               StorageMatrix[5][x] = Xcoord.getContents();   
               StorageMatrix[6][x] = Ycoord.getContents();
               StorageMatrix[7][x] = Emotion.getContents();
               StorageMatrix[8][x] = Current_Date.getContents();
               StorageMatrix[9][x] = Current_Time.getContents();
            }
            workbook1.close();
            
            
         WritableWorkbook workbook  = Workbook.createWorkbook(new File("/Users/"+ S +"/Desktop/"+FileName+".xls"));
           WritableSheet sheet = workbook.createSheet("First Sheet", 0);
          // Store values from the old speadsheet back to the spreadsheet
           String Oldval = " ";
           for(int a = 0;a<rows; a++)
           {
               for(int b = 0; b<columns; b++) 
               { 
                   Oldval = StorageMatrix[b][a];
                   Label OldColumn = new Label(b,a, Oldval);
                   sheet.addCell(OldColumn);    
               }
           } 
         
        //System.out.print(Data);
        for(int y = 1; y< 156 ; y++){
 
            //System.out.println(Data.get(String.valueOf(y)));
           ArrayList<String> list = (ArrayList<String>) Data.get(String.valueOf(y));
           Label FirstName_ = new Label(0, rows-1 + y, list.get(0)); 
           sheet.addCell(FirstName_); 
           Label LastName_ = new Label(1, rows-1 + y, list.get(1)); 
           sheet.addCell(LastName_); 
           Label UserNumber_  = new Label(2, rows-1 + y, list.get(2));
           sheet.addCell(UserNumber_);
           Label Song  = new Label(3, rows-1 + y, list.get(3));
           sheet.addCell(Song);
           Label Time  = new Label(4, rows-1 + y, list.get(4));
           sheet.addCell(Time);
           Label Xcoordinate  = new Label(5, rows-1 + y,list.get(5));
           sheet.addCell(Xcoordinate);
           Label Ycoordinate  = new Label(6, rows-1 + y,list.get(6));
           sheet.addCell(Ycoordinate);
           Label Emotion  = new Label(7, rows-1 + y, list.get(7));
           sheet.addCell(Emotion);
           Label current_date  = new Label(8, rows-1 + y,list.get(8));
           sheet.addCell(current_date);
           Label current_time  = new Label(9, rows-1 + y, list.get(9));
           sheet.addCell(current_time);
        }
        
           workbook.write();
           workbook.close(); 
            
            
   
    }

    @Override
    public void put(String FileName) throws Exception {
        StoreData(FileName);
    }
    
}
