/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
/ * and open the template in the editor.
 */
package Mturk_EmotionGraph.utils;

import java.io.File;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author josephappeah
 */
public class CreateSpreadsheet implements SpreadsheetInterface {
    
    
    
        public void CreateFile(String FileName) throws Exception
    {
        
          String S = System.getProperty("user.name");
          WritableWorkbook workbook  = Workbook.createWorkbook(new File("/Users/"+ S +"/Desktop/"+ FileName+".xls"));
          WritableSheet sheet = workbook.createSheet("First Sheet", 0);
           Label FirstName_ = new Label(0, 0, "First Name"); 
           sheet.addCell(FirstName_); 
           Label LastName_ = new Label(1, 0, "Last Name"); 
           sheet.addCell(LastName_); 
           Label UserNumber_  = new Label(2, 0, "User Number");
           sheet.addCell(UserNumber_);
           Label Song  = new Label(3, 0, "Song");
           sheet.addCell(Song);
           Label Time  = new Label(4, 0, "Time(Seconds)");
           sheet.addCell(Time);
           Label Xcoord  = new Label(5, 0, "X coordinate");
           sheet.addCell(Xcoord);
           Label Ycoord  = new Label(6, 0, "Y coordinate");
           sheet.addCell(Ycoord);
           Label Emotion  = new Label(7, 0, "Emotion");
           sheet.addCell(Emotion);
           Label Current_Date  = new Label(8, 0, "Current Date");
           sheet.addCell(Current_Date);
           Label Current_Time  = new Label(9, 0, "Current Time");
           sheet.addCell(Current_Time);
           workbook.write();
           workbook.close();       
    }   

    @Override
    public void put(String FileName) throws Exception {
        CreateFile(FileName);
    }
    
}
