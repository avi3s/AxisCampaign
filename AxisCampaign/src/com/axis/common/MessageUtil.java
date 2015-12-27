/**
 * This class is get a data from properties file
 * */


package com.axis.common;

import java.util.ResourceBundle;

import org.springframework.stereotype.Component;


@Component
public class MessageUtil {

	private static ResourceBundle resourceBundle = null;

	static {

		resourceBundle = ResourceBundle.getBundle("resources.i18n");
	}

	public String getBundle(String key) {

		return resourceBundle.getString(key);
	}
}
