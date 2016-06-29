package com.rovicorp.compare_rm_images_service.utils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONResponseGenerator {

	private ResultSet resultset = null;
	private String tulsa_url = null;
	private String aws_url = null;
	private ArrayList<String> imageurls = new ArrayList<String>();
	private ArrayList<Integer> indices = new ArrayList<Integer>();
	private ArrayList<String> imagecreationdates = new ArrayList<String>();
	private ArrayList<String> imageids = new ArrayList<String>();
	private Map<String, Object> imageMetadata = new HashMap<String, Object>();
	private Map<String, Object> CF_URLs = new HashMap<String, Object>();
	private Map<String, Object> CPS_URLs = new HashMap<String, Object>();

	private static final Logger logger = LoggerFactory.getLogger(JSONResponseGenerator.class);

	public JSONResponseGenerator(ResultSet resultset, String aws_url, String tulsa_url) {
		this.resultset = resultset;
		this.tulsa_url = tulsa_url;
		this.aws_url = aws_url;
	}

	public String generateJsonResponseString() {
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("result", imageMetadata);
		return jsonobject.toString();
	}

	public String execute() {
		obtainImageMetadata();
		appendServerNames();
		return generateJsonResponseString();
	}

	public void appendServerNames() {

		CF_URLs.clear();
		CPS_URLs.clear();
		int url_index = 0;

		for (int i = 0; i < imageurls.size(); i++) {
			indices.add(i);
		}

		Collections.shuffle(indices);

		for (int lgth = 0; lgth < imageurls.size(); lgth++) {

			String limelightURL = tulsa_url + "/2" + imageurls.get(indices.get(lgth));
			String cloudfrontURL = aws_url + "/2" + imageurls.get(indices.get(lgth));

			Map<String, String> CF_MetaData = new HashMap<String, String>();
			Map<String, String> CPS_MetaData = new HashMap<String, String>();

			CF_MetaData.put("URL", cloudfrontURL);
			CF_MetaData.put("DATE", imagecreationdates.get(indices.get(lgth)));
			CF_MetaData.put("ID", imageids.get(indices.get(lgth)));

			CPS_MetaData.put("URL", limelightURL);
			CPS_MetaData.put("DATE", imagecreationdates.get(indices.get(lgth)));
			CPS_MetaData.put("ID", imageids.get(indices.get(lgth)));

			CF_URLs.put(Integer.toString(url_index), CF_MetaData);
			CPS_URLs.put(Integer.toString(url_index), CPS_MetaData);

			url_index++;
		}

		imageMetadata.put("CF", CF_URLs);
		imageMetadata.put("CPS", CPS_URLs);

	}

	public void obtainImageMetadata() {
		logger.debug("Obtaining image metadata");
		try {
			while (resultset.next()) {
				imageurls.add(resultset.getString(5));
				imagecreationdates.add(resultset.getString(19));
				imageids.add(resultset.getString(2));
			}
		} catch (Exception e) {
			logger.error("Failed to obtain image metadata from resultset", e);
		}
	}

}
