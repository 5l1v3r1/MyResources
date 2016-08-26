package com.rovicorp.daq.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "image_types")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImageTypes {

	@XmlElement(name = "image_type")
	private List<ImageType> imagetypes = new ArrayList<>();

	public List<ImageType> getImageTypes() {
		return imagetypes;
	}

	public void setImageTypes(List<ImageType> imagetypes) {
		this.imagetypes = imagetypes;
	}
	
}
