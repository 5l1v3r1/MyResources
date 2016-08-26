package com.rovicorp.daq.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "aspect_ratios")
@XmlAccessorType(XmlAccessType.FIELD)
public class AspectRatios {
	
	@XmlElement(name = "aspect_ratios")
	private List<AspectRatio> aspectratios = new ArrayList<>();

	public List<AspectRatio> getAspectRatios() {
		return aspectratios;
	}

	public void setAspectRatios(List<AspectRatio> aspectratios) {
		this.aspectratios = aspectratios;
	}

}
