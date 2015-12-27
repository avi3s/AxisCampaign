package com.axis.exception;

public class EmptyValueException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	private String name;

	public EmptyValueException(String name) {
		super(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
