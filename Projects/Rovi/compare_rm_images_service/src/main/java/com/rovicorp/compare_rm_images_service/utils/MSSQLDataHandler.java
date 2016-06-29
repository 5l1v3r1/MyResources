package com.rovicorp.compare_rm_images_service.utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.rovicorp.compare_rm_images_service.interfaces.MSSQLConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MSSQLDataHandler implements MSSQLConnector{
	
	private  	ResultSet		Data		= null;
	private  	Statement    	State 		= null;
	private 	Connection 		connect 	= null;
	
	private  	String 			servername 	= null;
	private  	String 			query      	= null;
        private 	String 			username    = null;
	private  	String 			password    = null;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MSSQLDataHandler.class);
	
	public MSSQLDataHandler(String servername, String username, String password, String query){
		this.servername 	= 	servername;
		this.username 		= 	username;
		this.password 		= 	password;
		this.query  		= 	query;
	}
	
        
	public void openConnection() throws Exception{	
		
		try {  
			
				DriverManagerDataSource ds = new DriverManagerDataSource(); 
				ds.setUrl("jdbc:jtds:sqlserver://"+ servername +";useNTLMv2=true;domain=CORPORATE");
				ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
				ds.setPassword(password);
				ds.setUsername(username);
				connect = ds.getConnection();
				
		}catch(Exception e){
			
				logger.error("Failed to set up connection with {}",servername,e);
				throw e;
        }
		
            
		try{
				logger.info("Executing query:{}",query);
				State = connect.createStatement();
				Data  = State.executeQuery(query);
				
		}catch(Exception e){
			
				logger.error("Failed to execute query:{}", query,e);
				throw e;
				
		}
	}

	
	public void closeConnection() throws Exception {
		
		try{
			
				logger.info("Closing connection...");
				Data.close();
				connect.close();
				
		} catch (Exception e){
			
			logger.error("Failed to close connection with {}",servername,e);
			
		}	
		
	}


	public ResultSet executeRequest() throws Exception {
		
		openConnection();
		
		try{
			return Data;
		} catch(Exception e){
			logger.debug("Failed to return result set for query: {} to {}",query,servername,e);
		}
		
		closeConnection();
		
		return null;
	}

	


}
