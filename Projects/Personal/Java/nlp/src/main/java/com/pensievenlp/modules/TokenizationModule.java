package com.pensievenlp.modules;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pensievenlp.resources.ConfigurationManager;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class TokenizationModule {
	

	private static Properties configs = null;
	private static final Logger logger = LoggerFactory.getLogger(TokenizationModule.class);
	
	public TokenizationModule(ConfigurationManager cm) throws Exception {
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
		InputStream tokenizeHelper = null;
		TokenizerModel model = null;
		TokenizerME TokenDetector = null;
		
		try{
			tokenizeHelper = new FileInputStream(configs.getProperty("token-filepath"));
		} catch (Exception e){
			logger.error("Unable to obtain sentence file via {}. Ensure filepath is correct."
						, configs.getProperty("sentnce-filepath")
					);
		}
		
		try {
			model = new TokenizerModel(tokenizeHelper);
		} catch (Exception e) {
			logger.error("Unable to initailize sentence model. Ensure en-sent.bin filepath is available.");
		}
		
		try {
			TokenDetector = new TokenizerME(model);
		} catch (Exception e) {
			logger.error("Unable to initialize sentence detector. Ensure sentence model was properly initialized.");
		}
		
		return TokenDetector.tokenize(message);
	}

}
