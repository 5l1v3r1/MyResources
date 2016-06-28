package com.rovicorp.compare_rm_images.utils;

import java.util.ArrayList;
import java.io.File;
import java.sql.*;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetURLs {
	
	final static Logger logger 	= LoggerFactory.getLogger(GetURLs.class);
	ArrayList<String> URLs 		= new ArrayList<String>();
	ResultSet	Data			= null;
	Statement   State 			= null;
	String 		URLsForFile 	= null;
	File 		ImageURLs		= new File("src/main/resources/ImageURLs.txt");
	
	
	Connection 	connect 		= null;
	String 		servername 		= "tul1cipxdb18";
	private String username		= null;
	private  String password		= null;
	String 		query			= "SELECT TOP 500000 * FROM rm_image_file";
	String 		URL				= "jdbc:sqlserver://"+servername+"; user=" + username+"; password="+password+"; DatabaseName= Staging; ";
	
	
	public static void  main(String[] args) throws Exception{
		GetURLs p = new GetURLs();
		p.OpenConnection();
		p.getURLs();
		p.CloseConnection();
	}
	
	
	public void OpenConnection() throws Exception{	
		try{
			logger.debug("Setting up connection with {}.", servername);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			connect = DriverManager.getConnection(URL);
		}catch (Exception e){
			logger.info("Failed to connect to {}.", servername);
			logger.debug(e.getMessage());
		}
		
		try{
			logger.info("Executing query...");
			State = connect.createStatement();
			Data  = State.executeQuery(query);
		} catch(Exception e){
			logger.info("Failed to execute query:{}", query);
			logger.debug(e.getMessage());
		}

		
	}
	
	
	public void getURLs() throws Exception {
		while(Data.next()){
			try{
				logger.info("Getting URLs from resultset");
				URLs.add(Data.getString(4));
			} catch(Exception e){
				logger.info("Failed to get URLs");
				logger.debug(e.getMessage());
			}
		}
		
		
		for(String name : URLs){
			URLsForFile += name + "\n";
		}
		
		FileUtils.writeStringToFile(ImageURLs, URLsForFile);
		
	}
	
	
	public void CloseConnection() throws Exception{
		try{
			logger.info("Closing connection...");
			Data.close();
			connect.close();
		} catch (Exception e){
			logger.info("Failed to close connection");
			logger.debug(e.getMessage());
		}
	}
	

}
