package com.axis.change.logo;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Icons {
	private Icon[] icon;

	public Icon[] getIcon() {
		return icon;
	}

	public void setIcon(Icon[] icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "ClassPojo [icon = " + icon + "]";
	}
}
