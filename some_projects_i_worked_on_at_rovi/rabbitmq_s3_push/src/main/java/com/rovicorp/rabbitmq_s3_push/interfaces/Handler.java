package com.rovicorp.rabbitmq_s3_push.interfaces;

import java.util.Map;

public interface Handler {
	public void callback(Map<String,Object>  map, byte[] body);

	public void setstore(ImageStore store);
}
