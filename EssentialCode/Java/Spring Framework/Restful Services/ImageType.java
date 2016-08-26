package com.rovicorp.daq.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImageType {
	
	@XmlAttribute(name = "image_type")
	private long imageType;
	
	public void setImageType(long imageType){
		this.imageType = imageType;
	}
	
	public long getImageType(){
		return this.imageType;
	}

}
