package com.pensievenlp.modules;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pensievenlp.resources.ConfigurationManager;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetectionModule {
	
	private static Properties configs = null;
	private static final Logger logger = LoggerFactory.getLogger(SentenceDetectionModule.class);
	
	public SentenceDetectionModule() throws Exception {
		ConfigurationManager cm = new ConfigurationManager();
		configs = cm.getConfigs();
	}
	
	public String[] parse(String message) {
		return parseMessage(message);
	}
	
	public void train(String sentence) {
		trainParser(sentence);
	}
	
	protected void trainParser(String sentence){
		
	}
	
	protected String[] parseMessage(String message) {
		InputStream parseSentenceHelper = null;
		SentenceModel model = null;
		SentenceDetectorME sentenceDetector = null;
		
		try{
			parseSentenceHelper = new FileInputStream(configs.getProperty("sentnce-filepath"));
		} catch (Exception e){
			logger.error("Unable to obtain sentence file via {}. Ensure filepath is correct."
						, configs.getProperty("sentnce-filepath")
					);
		}
		
		try {
			model = new SentenceModel(parseSentenceHelper);
		} catch (Exception e) {
			logger.error("Unable to initailize sentence model. Ensure en-sent.bin filepath is available.");
		}
		
		try {
			sentenceDetector = new SentenceDetectorME(model);
		} catch (Exception e) {
			logger.error("Unable to initialize sentence detector. Ensure sentence model was properly initialized.");
		}
		
		return sentenceDetector.sentDetect(message);
	}
}
