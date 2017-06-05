package com.sist.daum;

import com.sun.xml.txw2.annotation.XmlElement;

public class Item {
	private String description;

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
