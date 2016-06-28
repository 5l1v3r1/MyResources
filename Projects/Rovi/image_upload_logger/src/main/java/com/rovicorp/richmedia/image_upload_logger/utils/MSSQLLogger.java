package com.rovicorp.richmedia.image_upload_logger.utils;

	import com.rovicorp.richmedia.image_upload_logger.interfaces.DataStorage;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.Statement;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.jdbc.datasource.DriverManagerDataSource;

	
public class MSSQLLogger implements DataStorage {

	private ResultSet Data = null;
	private Statement State = null;
	private Connection connect = null;

	private String servername = null;
	private String username = null;
	private String password = null;

	private static final Logger logger = LoggerFactory.getLogger(MSSQLLogger.class);

	public MSSQLLogger(String servername, String username, String password) {
		this.servername = servername;
		this.username = username;
		this.password = password;
	}

	private void openConnection() throws Exception {
		try {
			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setUrl("jdbc:jtds:sqlserver://" + servername + ";useNTLMv2=true;domain=CORPORATE");
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setPassword(password);
			ds.setUsername(username);
			connect = ds.getConnection();
		} catch (Exception e) {
			logger.error("Failed to set up connection with {}", servername, e);
			throw e;
		}
	}

	private void closeConnection() throws Exception {
		try {
			logger.info("Closing connection...");
			Data.close();
			connect.close();
		} catch (Exception e) {
			logger.error("Failed to close connection with {}", servername, e);
		}

	}
	
	private void executeQuery(String query) throws Exception{
		try {
			logger.info("Executing query:{}", query);
			State = connect.createStatement();
			State.executeQuery(query);
		} catch (Exception e) {
			logger.error("Failed to execute query:{}", query, e);
			throw e;
		}
	}
	
	public void store(String filepath, byte[] Bytes, String TimeStamp) throws Exception {
		//Need mssql table structure to create query.
		openConnection();
		try{
			executeQuery(null);
		}catch(Exception e){
			logger.error("Failed to execute query, {}",null,e);
			throw e;
		}
		closeConnection();
	}

}
