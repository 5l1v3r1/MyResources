package com.pensievenlp.application;

import com.pensievenlp.modules.NameDetectionModule;
import com.pensievenlp.modules.SentenceDetectionModule;
import com.pensievenlp.modules.TokenizationModule;
import com.pensievenlp.resources.ConfigurationManager;

import opennlp.tools.util.Span;

public class Parser {
	
	
	
	public static void main(String[] args) {
	
		try{
			ConfigurationManager cm = new ConfigurationManager();
			SentenceDetectionModule sm = new SentenceDetectionModule(cm);
			NameDetectionModule nm = new NameDetectionModule(cm);
			TokenizationModule tm = new TokenizationModule(cm);
			String message = "I am a boy. My Name is Paul.";
			
			for (String sentence : sm.parse(message)) {
				//System.out.println(sentence);
			}
			
			for (String sentence : tm.parse(message)) {
				//System.out.println(sentence);
			}
			
			for (Span sentence : nm.parse(message)) {
				
				System.out.println(sentence);
				
			}
			
			
			
		} catch(Exception e){
			
		}

	}
	
	
}
