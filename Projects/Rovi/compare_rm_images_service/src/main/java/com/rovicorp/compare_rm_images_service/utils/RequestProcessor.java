package com.rovicorp.compare_rm_images_service.utils;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.rovicorp.compare_rm_images_service.interfaces.DependencyInjector;
import com.rovicorp.compare_rm_images_service.interfaces.MSSQLConnector;
import com.rovicorp.compare_rm_images_service.interfaces.RestRequestProcessor;

public class RequestProcessor implements RestRequestProcessor{

	private 	DependencyInjector 	di				= null;
	
	public RequestProcessor(DependencyInjector di){
		this.di = di;
	}
	

	@Override
	public String specificImageRequest(String aws_environment, boolean is_tulsa_environment, int image_id) throws Exception{
		MSSQLConnector 		mssqlconnect 	= null;
		if(is_tulsa_environment){

				try{
					mssqlconnect = new MSSQLDataHandler(di.getQAServerName(),di.getUsername(),di.getPassword(),di.getQASpecificImageQuery()+image_id);
				}catch(Exception e){
					//code
					throw e;
				}
                                
                                
                          ResultSet	queryresults	= null;

		try{
			
				queryresults = mssqlconnect.executeRequest();
				
		}catch(Exception e){
			
				throw e;
				
		}
		
		JSONResponseGenerator 	astu 	= new JSONResponseGenerator(queryresults,generateAwsUrl(aws_environment),"http://qa-cps-static.rovicorp.com");
		
		try{
			
				return astu.execute();
				
		}catch(Exception e){
			//
		}
			
		}else{
			
				try{
					mssqlconnect = new MSSQLDataHandler(di.getProdServerName(),di.getUsername(),di.getPassword(),di.getProdSpecificImageQuery()+image_id);
				}catch(Exception e){
					//code
					throw e;
				}
                                
                                
                                
                                		ResultSet	queryresults	= null;

		try{
			
				queryresults = mssqlconnect.executeRequest();
				
		}catch(Exception e){
			
				throw e;
				
		}
		
		JSONResponseGenerator 	astu 	= new JSONResponseGenerator(queryresults,generateAwsUrl(aws_environment),di.getTulsaServerUrl());
		
		try{
			
				return astu.execute();
				
		}catch(Exception e){
			//
		}
		}
		
		
		return null;
	}
	
	
	

	@Override
	public String recentImageRequest(String aws_environment,boolean is_tulsa_environment) throws Exception {
		MSSQLConnector 		mssqlconnect 	= null;
		if(is_tulsa_environment){
			
			try{
				mssqlconnect = new MSSQLDataHandler(di.getQAServerName(),di.getUsername(),di.getPassword(),null);
			}catch(Exception e){
				//code
				throw e;
			}
		
	}else{
		
			try{
				mssqlconnect = new MSSQLDataHandler(di.getProdServerName(),di.getUsername(),di.getPassword(),generateRecentQuery());
			}catch(Exception e){
				//code
				throw e;
			}			
	}
	
	ResultSet	queryresults	= null;

	try{
		
		queryresults = mssqlconnect.executeRequest();
		
	}catch(Exception e){
		
		throw e;
		
	}
	
	JSONResponseGenerator 	astu 	= new JSONResponseGenerator(queryresults,generateAwsUrl(aws_environment),di.getTulsaServerUrl());
	
	try{
		
		return astu.execute();
		
	}catch(Exception e){
		//
	}
	
	return null;

	}
	
	

	@Override
	public String randomImageRequest(String aws_environment,boolean is_tulsa_environment) throws Exception {
		MSSQLConnector 		mssqlconnect 	= null;
		if(is_tulsa_environment){
			
			try{
				mssqlconnect = new MSSQLDataHandler(di.getQAServerName(),di.getUsername(),di.getPassword(),null);
			}catch(Exception e){
				//code
				throw e;
			}
		
	}else{
		
			try{
				mssqlconnect = new MSSQLDataHandler(di.getProdServerName(),di.getUsername(),di.getPassword(),di.getProdRandomImageQuery());
			}catch(Exception e){
				//code
				throw e;
			}			
	}
	
	ResultSet	queryresults	= null;

	try{
		
			queryresults = mssqlconnect.executeRequest();
			
	}catch(Exception e){
		
		throw e;
		
	}
	
	JSONResponseGenerator 	astu 	= new JSONResponseGenerator(queryresults,generateAwsUrl(aws_environment),di.getTulsaServerUrl());
	
	try{
		
		return astu.execute();
		
	}catch(Exception e){
		//
	}
	
	return null;
	}
	
	public String generateAwsUrl(String aws_environment){
		if("uat".equals(aws_environment.toLowerCase())){
			return di.getAwsUatUrl();
		}else if("qa".equals(aws_environment.toLowerCase())){
			return di.getAwsQaUrl();
		}else if("dev".equals(aws_environment.toLowerCase())){
			return di.getAwsDevUrl();
		}
		return null;
	}
	
	
    public String generateRecentQuery(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
        Calendar  calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,- di.getProdRecentNumberOfDays());
        return di.getProdRecentImageQuery() +"'" + df.format(calendar.getTime())+"'";
    }
    
}
