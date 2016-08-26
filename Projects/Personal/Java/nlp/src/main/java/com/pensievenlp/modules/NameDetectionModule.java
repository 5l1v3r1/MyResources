package com.pensievenlp.modules;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pensievenlp.resources.ConfigurationManager;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;

import opennlp.tools.util.Span;

public class NameDetectionModule {
	private static 	ConfigurationManager cm;
	private static Properties configs = null;
	private static final Logger logger = LoggerFactory.getLogger(NameDetectionModule.class);
	
	public NameDetectionModule(ConfigurationManager cm) throws Exception {
		NameDetectionModule.cm = cm;
		configs = cm.getConfigs();
		}
	
	public Span[] parse(String message) throws Exception {
			return parseMessage(message);
		}
	
	public void train(String sentence) {
			trainParser(sentence);
		}
	
	protected void trainParser(String sentence){
		
		}
	
	protected Span[] parseMessage(String message) throws Exception{
		InputStream nameHelper = null;
		TokenNameFinderModel model = null;
		NameFinderME nameDetector = null;
		
		try{
			nameHelper = new FileInputStream(configs.getProperty("name-filepath"));
		} catch (Exception e){
			logger.error("Unable to obtain sentence file via {}. Ensure filepath is correct."
						, configs.getProperty("sentnce-filepath")
					);
		}
		
		try {
			model = new TokenNameFinderModel(nameHelper);
		} catch (Exception e) {
			logger.error("Unable to initailize sentence model. Ensure en-sent.bin filepath is available.");
		}
		
		try {
			nameDetector = new NameFinderME(model);
		} catch (Exception e) {
			logger.error("Unable to initialize sentence detector. Ensure sentence model was properly initialized.");
		}
		
		return nameDetector.find(new TokenizationModule(cm).parse(message));
	}
}
