package com.joeycorp.webapp.lanceapp.modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public interface GetMetadata {
	public String executeImageSearchResult(String queryparam) throws FileNotFoundException, IOException, MalformedURLException;
	public String executeComputerVision(File image) throws FileNotFoundException, IOException, MalformedURLException;
}
