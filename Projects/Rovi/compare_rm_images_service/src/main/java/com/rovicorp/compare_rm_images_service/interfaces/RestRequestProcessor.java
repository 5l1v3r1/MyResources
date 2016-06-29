package com.rovicorp.compare_rm_images_service.interfaces;

public interface RestRequestProcessor {
	public String specificImageRequest(String aws_environment, boolean is_tulsa_environment, int image_id) throws Exception;
	public String recentImageRequest(String aws_environment, boolean is_tulsa_environment) throws Exception;
	public String randomImageRequest(String aws_environment, boolean is_tulsa_environment) throws Exception;
}
