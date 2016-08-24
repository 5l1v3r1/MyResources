package com.pensievenlp.application;

import com.pensievenlp.modules.SentenceDetectionModule;

public class Parser {

	public static void main(String[] args) {
		try{
			SentenceDetectionModule pis = new SentenceDetectionModule();
			String message = "I am a boy";
			for (String sentence : pis.parse(message)){
				System.out.println(sentence);
			}
			
		} catch(Exception e){
			
		}

	}
	
	
}
