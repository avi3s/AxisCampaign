package com.axis.change.logo;

import javax.xml.bind.annotation.XmlAttribute;

public class File {
	
	private String name;

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ClassPojo [name = " + name + "]";
	}
}
