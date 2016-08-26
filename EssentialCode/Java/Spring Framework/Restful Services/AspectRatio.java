package com.rovicorp.daq.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class AspectRatio {
	
	@XmlAttribute(name = "aspect_ratio")
	private String aspectRatio;
	
	public void setAspectRatio(String aspectRatio){
		this.aspectRatio = aspectRatio;
	}
	
	public String getAspectRatio(){
		return this.aspectRatio;
	}

}
