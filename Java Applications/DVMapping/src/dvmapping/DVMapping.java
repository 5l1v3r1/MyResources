
package dvmapping;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.util.Iterator;
import java.util.Scanner;



public class DVMapping 
{
    private static final String NEW_LINE_SEPARATOR = "\n"; // Seperator for creating new lines in the exported CSV file
    private static final Object [] FILE_HEADER = {"Name","First Access","Last Access","Hits"}; //Header for mapping when exporting updated data to CSV file
    private static final String [] FILE_HEADER_MAPPING = {"Name","First Access","Last Access","Hits"}; //Header for mapping when importing data
    Map Data = new HashMap(); //HashMap to store data from the spreadsheets

    public static void main(String[] args) throws IOException 
    {
       DVMapping p = new DVMapping();  
       Scanner FolderDir= new Scanner(System.in); // Initiating Scanner class to get the folder directory
       System.out.println("Enter Folder Directory->>:  ");
       String Directory=FolderDir.next(); //
       p.Update(Directory); // The Update object runs the entire program
    }
   
    public void DataCollection(String fileDir)
    {
       
    FileReader fileReader = null; 
    CSVParser csvFileParser = null;
    CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
        try {
            fileReader = new FileReader(fileDir);
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            List csvRecords = csvFileParser.getRecords(); 
            for (int i = 0; i < csvRecords.size(); i++)
            {
                CSVRecord record =  (CSVRecord) csvRecords.get(i);   
                ArrayList<String> DataStore = new ArrayList<String>(); //New instance of ArrayList to store CSV file
                DataStore.add((record.get("First Access"))); //Storing First Access date and time to update/add to data in the HashMap
                DataStore.add((record.get("Last Access"))); //Storing Last Access date and time to update/add to data in the HashMap
                DataStore.add((record.get("Hits"))); //Storing the Hitcount to update/add to data in the HashMap
                
                if(!Data.containsKey(record.get("Name")) && (!record.get("Name").equals("Name")) )   //Comparing Usernames to ensure there are no duplicates
                {
                    Data.put(record.get("Name"), DataStore);    //Adding New Username if it does not already exist
                }
                else if(Data.containsKey(record.get("Name")) && (!record.get("Name").equals("Name")))   //To update the Hashmap if the record exists
                {
                    ArrayList<String> list = (ArrayList<String>) Data.get(record.get("Name")); //list gets the value data from the hashmap
                    ArrayList<String> NewDataStore = new ArrayList<String>();   //Arraylist to replace old value if there are any updates
                try{
    
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); //initiating new SDF to compare the Access times. z represents the timezone
        	Date NewFirstAccess = sdf.parse(record.get("First Access"));   //Get new first date
        	Date OldFirstAccess = sdf.parse(list.get(0));                  //Get stored first date from Map
                Date NewLastAccess = sdf.parse(record.get("Last Access")); 
        	Date OldLastAccess = sdf.parse(list.get(1));

            //comparing the data       	
        	if(OldFirstAccess.after(NewFirstAccess)){               // If the Stored access date > The new access date,
                NewDataStore.add(record.get("First Access"));       // Store new date in NewDataStore, 
        	}
                else{                                               // Else, store old date
                NewDataStore.add(list.get(0));
                }
                if(NewLastAccess.after(OldLastAccess)){
                NewDataStore.add(record.get("Last Access"));
        	}
                else{
                NewDataStore.add(list.get(1));
                }
                if(Integer.parseInt(record.get("Hits")) > Integer.parseInt(list.get(2))){
                NewDataStore.add(record.get("Hits"));
        	}
                else{
                NewDataStore.add(list.get(2));
                }   
                }catch(ParseException ex){
    		ex.printStackTrace();
                }  
                Data.put(record.get("Name"), NewDataStore);  
                }  
            }
            
            } 
        catch (Exception e) 
            {
            System.out.println("Error in CsvFileReader");
            e.printStackTrace();
            } finally 
            {
        try 
            {
                fileReader.close();
                csvFileParser.close();
            } 
        catch (IOException e) 
            {
                System.out.println("There was an error while closing fileReader/csvFileParser");
                e.printStackTrace();
            }
        }  
        
}
    
    
    
    public void ExportData(String FileDirectory) throws IOException
    {

        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            fileWriter = new FileWriter(FileDirectory+"/Updatedlist.csv");
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            csvFilePrinter.printRecord(FILE_HEADER);
            
        Iterator it = Data.entrySet().iterator();
        while (it.hasNext())
        {
        List DataviewerRecords = new ArrayList();
        Map.Entry pair = (Map.Entry)it.next();
        ArrayList<String> list = (ArrayList<String>) Data.get(pair.getKey());
        DataviewerRecords.add(pair.getKey());
        DataviewerRecords.add(list.get(0));
        DataviewerRecords.add(list.get(1));
        DataviewerRecords.add(list.get(2));
        csvFilePrinter.printRecord(DataviewerRecords);
        it.remove();
        }
            
            //System.out.println("CSV Created successfully !!!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }
    


    public void Update(String FolderDirectory) throws IOException
    {
        int ifCSV = 0;
        String directoryPath = FolderDirectory;
        File[] filesInDirectory = new File(directoryPath).listFiles();
        for(File f : filesInDirectory)
        {
        String filePath = f.getAbsolutePath();
        String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
        
        if("csv".equals(fileExtenstion))
        {
        ifCSV++;
        DVMapping p = new DVMapping();
        p.DataCollection(filePath);
        p.ExportData(FolderDirectory);
        }
        } 
        if(ifCSV >0)
        {
          System.out.println("Done! -Check same directory for updatedlist.csv");  
        }
        else{
         System.out.println("Done! -There were no CSV files in the directory.");   
        }

        
    }
    
    
}



    
    



