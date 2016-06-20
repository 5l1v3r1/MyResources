package com.josephappeah.corporate.image_name_generator.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

public class NounPhraseGenerator {
static String sentence = "a items car jump random get. i on boat a table chair plurar car";
	
	static Set<String> nounPhrases = new HashSet<>();
	
	public ArrayList<String> execute(String sentence){
		ArrayList<String> returnNounPhrases = new ArrayList<String>();
		InputStream modelInParse = null;
		try {
			//load chunking model
			modelInParse = new FileInputStream("src/main/resources/en-parser-chunking.bin"); //from http://opennlp.sourceforge.net/models-1.5/
			ParserModel model = new ParserModel(modelInParse);
			
			//create parse tree
			Parser parser = ParserFactory.create(model);
			Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
			
			//call subroutine to extract noun phrases
			for (Parse p : topParses)
				getNounPhrases(p);
			
			for (String s : nounPhrases)
				returnNounPhrases.add(s);
			nounPhrases = new HashSet<>();
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelInParse != null) {
		    try {
		    	modelInParse.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		
		return returnNounPhrases;
	}
	
	//recursively loop through tree, extracting noun phrases
	public static void getNounPhrases(Parse p) {
			
	    if (p.getType().equals("NP")) { //NP=noun phrase
	         nounPhrases.add(p.getCoveredText());
	    }
	    for (Parse child : p.getChildren())
	         getNounPhrases(child);
	}

}
