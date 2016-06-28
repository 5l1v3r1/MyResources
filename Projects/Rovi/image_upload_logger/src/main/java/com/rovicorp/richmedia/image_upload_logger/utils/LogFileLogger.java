package com.rovicorp.richmedia.image_upload_logger.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rovicorp.richmedia.image_upload_logger.interfaces.DataStorage;

public class LogFileLogger implements DataStorage {

	private static final Logger logger = LoggerFactory.getLogger(LogFileLogger.class);
	private File ImageTransferLogFile = new File("src/main/resources/ImageTransferLogs.log");

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
	private Calendar calendar = Calendar.getInstance();

	
	public static void main(String[] args[]) throws Exception{
		LogFileLogger lfl = new LogFileLogger();
		lfl.store("path", "Bytes".getBytes(), null);
	}
	
	
	public void store(String FilePath, byte[] Bytes, String TimeStamp) throws Exception {
		
		if(TimeStamp.equals(null)){
			calendar.add(Calendar.DATE, 0);
			TimeStamp = df.format(calendar.getTime());
		}

		logger.debug("Storing:\nFilePath: {}\nByteSize: {}\nTimeStamp: {}\nTo log file {}", FilePath,
				Bytes.length, TimeStamp, ImageTransferLogFile);
		
		String LineFormat = "FilePath=" + FilePath + "ByteSize=" + Bytes.length + "TimeStamp=" + TimeStamp;
		
		try {
			FileUtils.writeStringToFile(ImageTransferLogFile, LineFormat + "\n", true);
		} catch (Exception e) {
			logger.error("Failed to store image data to {}", ImageTransferLogFile, e);
		}
	}

}
