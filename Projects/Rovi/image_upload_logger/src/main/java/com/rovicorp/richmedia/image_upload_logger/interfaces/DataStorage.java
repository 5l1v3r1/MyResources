package com.rovicorp.richmedia.image_upload_logger.interfaces;


public interface DataStorage {
	public void store(String filepath, byte[] Bytes, String TimeStamp) throws Exception;
}
