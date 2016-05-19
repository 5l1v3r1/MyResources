package com.rovicorp.rabbitmq_s3_push.interfaces;

public interface ImageStore {
	public void put(String key,byte[] value);
}
