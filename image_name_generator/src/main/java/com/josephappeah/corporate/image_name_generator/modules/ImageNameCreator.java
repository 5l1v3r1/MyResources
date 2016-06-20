package com.josephappeah.corporate.image_name_generator.modules;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.josephappeah.corporate.image_name_generator.interfaces.ResponseIntepretor;
import com.josephappeah.corporate.image_name_generator.utils.MarkovSentenceGenerator;

import net.jeremybrooks.knicker.Knicker.RelationshipType;
import net.jeremybrooks.knicker.KnickerException;
import net.jeremybrooks.knicker.WordApi;
import net.jeremybrooks.knicker.dto.Related;

public class ImageNameCreator implements ResponseIntepretor{

	private static final Logger logger = LoggerFactory.getLogger(ImageNameCreator.class);
	private static ArrayList<String> allwords = new ArrayList<String>();
	private static String cv_data_recieved = null;
	private static String markovrequeststring = "";
	private static String markovsentence = null;
	
	public void setResponseData(InputStream in) {
		try{
			logger.debug("Parsing recieved input to String");
			cv_data_recieved = IOUtils.toString(in);
		}catch(Exception e){
			logger.error("Failed to parse recived input stream to string",e);
		}
	}

	public void processResponseData() {
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobject = null;
		
		try{
			logger.debug("Parsing computer vision json data.");
			jsonobject = (JSONObject) jsonparser.parse(cv_data_recieved);
		}catch(Exception e){
			logger.error("Failed to parse computer vision json data.",e);
		}

		
		logger.debug("{}",jsonobject.get("description.captions").toString());
		String imagedescription = jsonobject.get("captions").toString();
		Integer descriptionconfidence = Integer.parseInt(jsonobject.get("confidence").toString());
		
		if( descriptionconfidence > 0.65){
			markovsentence = imagedescription;
		}else{
			String[] imagedescriptionwords = imagedescription.split(" ");
		
			for(String word : imagedescriptionwords){
				allwords.add(word);
				try {
					List<Related> synonymsofword = WordApi.related(word, false, EnumSet.of(RelationshipType.synonym),2);
					for(Related synonym : synonymsofword){
						allwords.add(synonym.toString());
					}
				}catch (KnickerException e) {
					logger.error("Failed to get synonyms from wordnik",e);
				}
			}
		
		
			for(String word: allwords ){
				markovrequeststring.concat(" "+word);
			}
		
			try{
				logger.debug("Generating Markov Sentence");
				markovsentence = MarkovSentenceGenerator.execute(markovrequeststring);
			}catch(Exception e){
				logger.error("Failed to generate markov sentence.",e);
			}
		}
		
	}

	public String getProcessedData() {
		return markovsentence;
	}

}

