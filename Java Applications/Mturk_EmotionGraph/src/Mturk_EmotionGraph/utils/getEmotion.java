/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mturk_EmotionGraph.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import Mturk_EmotionGraph.utils.variables;
import Mturk_EmotionGraph.utils.variables;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author josephappeah
 */
public class getEmotion {
    variables setvariables = new variables();
    public String FirstName;
    public String LastName;
    public String UserNumber;
    
    Map Data = new HashMap();
    
       public void Emotion(int x , int y, int t){
       String emotion = "";
       double xcoord;
       double ycoord;
       /*double Xout;
       double Yout;
       if     ((y>=320)&&(y<=356)&&(x>=390)&&(x<=525)){emotion = "Expectant";} //Expectant
       else if((y>=320)&&(y<=356)&&(x>=525)&&(x<=646)){emotion = "Interested";} //Interested
       else if((y>=289)&&(y<=356)&&(x>=646)&&(x<=690)){emotion = "Joyous";} //Joyous
       else if((y>=289)&&(y<=356)&&(x>=690)&&(x<=735)){emotion = "Happy";} //Happy
       else if((y>=289)&&(y<=320)&&(x>=390)&&(x<=525)){emotion = "Passionate";} //Passionate
       else if((y>=289)&&(y<=320)&&(x>=525)&&(x<=646)){emotion = "Amused";} //Amused
       else if((y>=230)&&(y<=289)&&(x>=390)&&(x<=525)){emotion = "Light Hearted";} //Light Hearted
       else if((y>=260)&&(y<=289)&&(x>=560)&&(x<=620)){emotion = "Determined";} //Determined
       else if((y>=230)&&(y<=260)&&(x>=560)&&(x<=620)){emotion = "Enthusiastic";} //Enthusiastic
       else if((y>=230)&&(y<=260)&&(x>=620)&&(x<=735)){emotion = "Delighted";} //Delighted
       else if((y>=190)&&(y<=230)&&(x>=390)&&(x<=560)){emotion = "Convinced";} //Convinced
       else if((y>=150)&&(y<=190)&&(x>=390)&&(x<=560)){emotion = "Feeling Superior";}//Feeling Superior
       else if((y>=150)&&(y<=190)&&(x>=560)&&(x<=646)){emotion = "Courageous";} //Courageous
       else if((y>=150)&&(y<=190)&&(x>=646)&&(x<=735)){emotion = "Elated";} //Elated
       else if((y>=125)&&(y<=150)&&(x>=390)&&(x<=450)){emotion = "Conceited";} //Conceited
       else if((y>=125)&&(y<=150)&&(x>=450)&&(x<=560)){emotion = "Ambitious";} //Ambitious
       else if((y>=125)&&(y<=150)&&(x>=560)&&(x<=735)){emotion = "Self Confident";} //Self Confident
       else if((y>=80)&&(y<=125)&&(x>=540)&&(x<=610)){emotion = "Triumphant";} //Triumphant
       else if((y>=105)&&(y<=125)&&(x>=610)&&(x<=735)){emotion = "Excited";} //Excited
       else if((y>=80)&&(y<=105)&&(x>=610)&&(x<=735)){emotion = "High Power/Control";} //High Power/Control
       else if((y>=70)&&(y<=80)&&(x>=390)&&(x<=560)){emotion = "Lusting";} //Lusting
       else if((y>=40)&&(y<=50)&&(x>=450)&&(x<=560)){emotion = "Aroused";} //Aroused
       else if((y>=50)&&(y<=70)&&(x>=450)&&(x<=560)){emotion = "Astonished";} //Astonished
       else if((y>=40)&&(y<=70)&&(x>=560)&&(x<=735)){emotion = "Adventurous";} //Adventurous
       else if((y>=15)&&(y<=40)&&(x>=450)&&(x<=560)){emotion = "Alert";} //Alert
       else if((y>=0)&&(y<=30)&&(x>=390)&&(x<=440)){emotion = "Aroused";} //Aroused
       else if((y>=356)&&(y<=396)&&(x>=460)&&(x<=560)){emotion = "Impressed";} //Impressed
       else if((y>=356)&&(y<=386)&&(x>=646)&&(x<=720)){emotion = "Feel Well";} //Feel Well
       else if((y>=356)&&(y<=389)&&(x>=720)&&(x<=774)){emotion = "Pleasant";} //Pleasant
       else if((y>=389)&&(y<=416)&&(x>=416)&&(x<=673)){emotion = "Amorous";} //Amorous
       else if((y>=386)&&(y<=400)&&(x>=673)&&(x<=774)){emotion = "Pleased";} //Pleased
       else if((y>=411)&&(y<=435)&&(x>=525)&&(x<=560)){emotion = "Confident";} //Confident
       else if((y>=406)&&(y<=420)&&(x>=709)&&(x<=774)){emotion = "Glad";} //Glad
       else if((y>=420)&&(y<=435)&&(x>=720)&&(x<=774)){emotion = "Contented";} //Contented
       else if((y>=445)&&(y<=465)&&(x>=550)&&(x<=595)){emotion = "Hopeful";} //Hopeful
       else if((y>=490)&&(y<=513)&&(x>=420)&&(x<=510)){emotion = "Longing";} //Longing
       else if((y>=505)&&(y<=520)&&(x>=505)&&(x<=560)){emotion = "Attentive";} //Attentive
       else if((y>=505)&&(y<=520)&&(x>=416)&&(x<=673)){emotion = "Solemn";} //Solemn
       else if((y>=490)&&(y<=535)&&(x>=673)&&(x<=725)){emotion = "Serene";} //Serene
       else if((y>=535)&&(y<=548)&&(x>=665)&&(x<=725)){emotion = "Content";} //Content
       else if((y>=552)&&(y<=563)&&(x>=410)&&(x<=450)){emotion = "Pensive";} //Pensive
       else if((y>=552)&&(y<=563)&&(x>=510)&&(x<=585)){emotion = "Contemplative";} //Contemplative
       else if((y>=552)&&(y<=563)&&(x>=600)&&(x<=645)){emotion = "Friendly";} //Friendly
       else if((y>=552)&&(y<=563)&&(x>=650)&&(x<=725)){emotion = "At Ease";} //At Ease
       else if((y>=563)&&(y<=572)&&(x>=650)&&(x<=725)){emotion = "Satisfied";} //Satisfied
       else if((y>=575)&&(y<=588)&&(x>=424)&&(x<=583)){emotion = "Serious";} //Serious
       else if((y>=575)&&(y<=588)&&(x>=517)&&(x<=555)){emotion = "Polite";} //Polite
       else if((y>=575)&&(y<=588)&&(x>=630)&&(x<=725)){emotion = "Relaxed";} //Relaxed
       else if((y>=588)&&(y<=595)&&(x>=630)&&(x<=590)){emotion = "Serious";} //Serious
       else if((y>=588)&&(y<=604)&&(x>=680)&&(x<=725)){emotion = "Relaxed";} //Relaxed
       else if((y>=604)&&(y<=620)&&(x>=630)&&(x<=680)){emotion = "Conductive";} //Conductive
       else if((y>=620)&&(y<=630)&&(x>=424)&&(x<=491)){emotion = "Conscientious";} //Conscientious
       else if((y>=620)&&(y<=630)&&(x>=580)&&(x<=625)){emotion = "Peaceful";} //Peaceful
       else if((y>=680)&&(y<=695)&&(x>=400)&&(x<=460)){emotion = "Sleepy";} //Sleepy
       else if((y>=670)&&(y<=685)&&(x>=470)&&(x<=515)){emotion = "Reverent";} //Reverent
       else if((y>=660)&&(y<=680)&&(x>=523)&&(x<=595)){emotion = "Compassionate";} //Compassionate
       else if((y>=695)&&(y<=706)&&(x>=390)&&(x<=426)){emotion = "Calm";} //Calm
       else if((y>=695)&&(y<=706)&&(x>=350)&&(x<=390)){emotion = "Passive";} //Passive
       else if((y>=680)&&(y<=695)&&(x>=346)&&(x<=390)){emotion = "Tired";} //Tired 
       else if((y>=670)&&(y<=685)&&(x>=250)&&(x<=300)){emotion = "Doubtful";} //Doubtful
       else if((y>=660)&&(y<=680)&&(x>=287)&&(x<=337)){emotion = "Droopy";} //Droopy
       else if((y>=630)&&(y<=650)&&(x>=175)&&(x<=225)){emotion = "Dejected";} //Dejected
       else if((y>=618)&&(y<=635)&&(x>=230)&&(x<=280)){emotion = "Bored";} //Bored
       else if((y>=618)&&(y<=635)&&(x>=150)&&(x<=200)){emotion = "Anxious";} //Anxious
       else if((y>=604)&&(y<=620)&&(x>=60)&&(x<=175)){emotion = "Low Power/Control";} //Low Power/Control
       else if((y>=590)&&(y<=610)&&(x>=240)&&(x<=290)){emotion = "Hesitant";} //Hesitant
       else if((y>=695)&&(y<=706)&&(x>=150)&&(x<=203)){emotion = "Wavering";} //Wavering
       else if((y>=588)&&(y<=604)&&(x>=310)&&(x<=380)){emotion = "Melancholic";} //Melancholic
       else if((y>=552)&&(y<=563)&&(x>=210)&&(x<=290)){emotion = "Embarrased";} //Embarrased
       else if((y>=535)&&(y<=570)&&(x>=50)&&(x<=110)){emotion = "Depressed";} //Depressed
       else if((y>=520)&&(y<=535)&&(x>=63)&&(x<=130)){emotion = "Desperate";} //Desperate
       else if((y>=520)&&(y<=535)&&(x>=190)&&(x<=255)){emotion = "Ashamed";} //Ashamed
       else if((y>=520)&&(y<=535)&&(x>=315)&&(x<=525)){emotion = "Languid";} //Languid
       else if((y>=505)&&(y<=520)&&(x>=120)&&(x<=200)){emotion = "Depressed";} //Depressed
       else if((y>=505)&&(y<=520)&&(x>=50)&&(x<=110)){emotion = "Gloomy";} //Gloomy
       else if((y>=490)&&(y<=505)&&(x>=250)&&(x<=310)){emotion = "Feel Guilt";} //Feel Guilt
       else if((y>=490)&&(y<=505)&&(x>=135)&&(x<=200)){emotion = "Despondent";} //Despondent
       else if((y>=485)&&(y<=590)&&(x>=80)&&(x<=120)){emotion = "Sad";} //Sad
       else if((y>=465)&&(y<=485)&&(x>=90)&&(x<=170)){emotion = "Uncomfortable";} //Uncomfortable
       else if((y>=455)&&(y<=480)&&(x>=315)&&(x<=375)){emotion = "Worried";} //Worried
       else if((y>=420)&&(y<=450)&&(x>=190)&&(x<=440)){emotion = "Taken Aback";} //Taken Aback
       else if((y>=390)&&(y<=420)&&(x>=250)&&(x<=340)){emotion = "Apathetic";} //Apathetic
       else if((y>=400)&&(y<=425)&&(x>=120)&&(x<=200)){emotion = "Dissatisfied";} //Dissatisfied
       else if((y>=380)&&(y<=400)&&(x>=75)&&(x<=160)){emotion = "Miserble";} //Miserable
       else if((y>=355)&&(y<=380)&&(x>=50)&&(x<=135)){emotion = "Dissapointed";} //Dissapointed
       else if((y>=320)&&(y<=356)&&(x>=85)&&(x<=160)){emotion = "Startled";} //Startled
       else if((y>=310)&&(y<=340)&&(x>=160)&&(x<=250)){emotion = "Distrustful";} //Distrustful
       else if((y>=289)&&(y<=320)&&(x>=90)&&(x<=165)){emotion = "Insulted";} //Insulted
       else if((y>=260)&&(y<=289)&&(x>=220)&&(x<=290)){emotion = "Suspicious";} //Suspicious
       else if((y>=260)&&(y<=289)&&(x>=70)&&(x<=270)){emotion = "Bitter";} //Bitter
       else if((y>=260)&&(y<=289)&&(x>=135)&&(x<=390)){emotion = "Impatient";} //Impatient
       else if((y>=230)&&(y<=260)&&(x>=90)&&(x<=180)){emotion = "Discontented";} //Discontented
       else if((y>=210)&&(y<=240)&&(x>=180)&&(x<=280)){emotion = "Frustrated";} //Frustrated
       else if((y>=289)&&(y<=320)&&(x>=20)&&(x<=95)){emotion = "Upset";} //Upset
       else if((y>=200)&&(y<=225)&&(x>=65)&&(x<=135)){emotion = "Loathing";} //Loathing
       else if((y>=170)&&(y<=200)&&(x>=60)&&(x<115)){emotion = "Stressed";} //Stressed
       else if((y>=180)&&(y<=200)&&(x>=115)&&(x<=180)){emotion = "Disgusted";} //Disgusted
       else if((y>=150)&&(y<=190)&&(x>=315)&&(x<=375)){emotion = "Jealous";}//Jealous
       else if((y>=150)&&(y<=190)&&(x>=150)&&(x<=240)){emotion = "Distressed";}//Distressed
       else if((y>=185)&&(y<=210)&&(x>=240)&&(x<=335)){emotion = "Indignant";}//Indignant
       else if((y>=125)&&(y<=150)&&(x>=240)&&(x<=325)){emotion = "Annoyed";} //Annoyed
       else if((y>=125)&&(y<=150)&&(x>=115)&&(x<=210)){emotion = "Contemptuous";} //Contemptuous
       else if((y>=105)&&(y<=125)&&(x>=270)&&(x<=350)){emotion = "Enraged";} //Enraged
       else if((y>=105)&&(y<=125)&&(x>=150)&&(x<=195)){emotion = "Defient";} //Defient
       else if((y>=90)&&(y<=130)&&(x>=100)&&(x<=150)){emotion = "Nervous";} //Nervous
       else if((y>=80)&&(y<=125)&&(x>=300)&&(x<=365)){emotion = "Afraid";} //Afraid
       else if((y>=80)&&(y<=125)&&(x>=195)&&(x<=260)){emotion = "Angry";} //Angry
       else if((y>=70)&&(y<=80)&&(x>=330)&&(x<=390)){emotion = "Tense";} //Tense
       else if((y>=70)&&(y<=80)&&(x>=150)&&(x<=210)){emotion = "Hateful";} //Hateful
       else if((y>=45)&&(y<=85)&&(x>=65)&&(x<=150)){emotion = "Obstructive";} //Obstructive
       else if((y>=50)&&(y<=70)&&(x>=300)&&(x<=380)){emotion = "Alarmed";} //Alarmed
       else if((y>=50)&&(y<=70)&&(x>=250)&&(x<=300)){emotion = "Hostile";} //Hostile
       else if((y>=15)&&(y<=50)&&(x>=220)&&(x<=295)){emotion = "Tense";} //Tense
       else if((y>=15)&&(y<=50)&&(x>=285)&&(x<=375)){emotion = "Bellicose";} //Bellicose
       else if((y>=0)&&(y<=30)&&(x>=350)&&(x<=390)){emotion = "Astonished";} //Astonished*/
       ArrayList<String> DataStore = new ArrayList<String>();  
       
       //Xout = 0.1*(((double)x-393)/35.0);
       //Yout = 0.1*(((double)y-360)/35.0);
       
       
       xcoord = (((double)x-399)/30);
       ycoord = (((double)y-374)/30);
       
       if(xcoord >= 9 && ycoord <= 1 && xcoord <= 12 && ycoord >= -1 ){emotion = "Pleasant";}
       if(xcoord >= 9 && ycoord <= 12 && xcoord <= 12 && ycoord >= 9 ){emotion = "High Power/Control";}
       
       if(xcoord >= -1 && ycoord <= 12 && xcoord <= 1 && ycoord >= 9 ){emotion = "Active/ Aroused";}
       if(xcoord >= -1 && ycoord <= -9 && xcoord <= 1 && ycoord >= -12 ){emotion = "Passive/ Calm";}
       
       if(xcoord >= -12 && ycoord <= 1 && xcoord <= -9 && ycoord >= -1 ){emotion = "Unpleasant";}
       if(xcoord >= -12 && ycoord <= -9 && xcoord <= -9 && ycoord >= -12 ){emotion = "Lower Power/Control";}
       
       if(xcoord >= -12 && ycoord <= 12 && xcoord <= -9 && ycoord >= 9 ){emotion = "Obstructive";}
       if(xcoord >= 9 && ycoord <= -9 && xcoord <= 12 && ycoord >= -12 ){emotion = "Constructive";}
       
       
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
        Date date = new Date();
       
       
       
       DataStore.add(FirstName);
       DataStore.add(LastName);
       DataStore.add(UserNumber);
       DataStore.add("null");
       DataStore.add(String.valueOf(t));
       DataStore.add(String.valueOf(xcoord));
       DataStore.add(String.valueOf(ycoord));
       DataStore.add(emotion);
       DataStore.add(dateFormat.format(date).split(" ")[0]);
       DataStore.add(dateFormat.format(date).split(" ")[1]);
       //System.out.println(DataStore);
       
       
       Data.put(String.valueOf(t), DataStore);
       
       //System.out.println(Data.get(String.valueOf(t)));
       //System.out.println(t);
       
       //setvariables.setVariables(null, null, 0, null, null, null, null, 0, DataStore);
    }
       
       
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
               Cell current_date = sheet1.getCell(8,x);
               Cell current_time = sheet1.getCell(9,x);
               StorageMatrix[0][x] = UserNumber_.getContents();
               StorageMatrix[1][x] = FirstName_.getContents();
               StorageMatrix[2][x] = LastName_.getContents();   
               StorageMatrix[3][x] = Song.getContents();
               StorageMatrix[4][x] = Time.getContents();
               StorageMatrix[5][x] = Xcoord.getContents();   
               StorageMatrix[6][x] = Ycoord.getContents();
               StorageMatrix[7][x] = Emotion.getContents();
               StorageMatrix[8][x] = current_date.getContents();
               StorageMatrix[9][x] = current_time.getContents();
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
       
       
       
       public Map getData(){
           System.out.print(Data);
                   
           return this.Data;
       }
    
}
