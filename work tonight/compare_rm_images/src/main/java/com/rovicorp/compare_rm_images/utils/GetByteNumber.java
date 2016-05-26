package com.rovicorp.compare_rm_images.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import ch.qos.logback.classic.Logger;

public class GetByteNumber {
	/*
	public static void main(String[] args) throws Exception{
		GetByteNumber b = new GetByteNumber();
		System.out.println(b.get("http://d7cks5tzzz8un.cloudfront.net/2/Open/Hulu_1693/Program/5097206/_derived_jpg_q90_75x75_m0/hulu0c6ddc3d0-a3c1-4c18-8ab9-6e57ae6f5d9a.jpg"));
		
	}*/
	
	public static int get(String urlString) throws Exception{
		URLConnection connection = null;
		InputStream in = null;
		URL url = new URL(urlString);
		try{
		 connection = url.openConnection();
		} catch(Exception e){
			e.printStackTrace();
		}
		try{
		 in = connection.getInputStream();
		} catch(Exception e){
			e.printStackTrace();
		}
		byte[] Byte = IOUtils.toByteArray(in);
		return Byte.length;
	}
	
	
	public static String fastGet(String urlString) throws Exception{
		String size = null;
		try{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(urlString);
		HttpResponse response = client.execute(request);
		size = response.getLastHeader("Content-Length").getValue();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return size;
	}

}
