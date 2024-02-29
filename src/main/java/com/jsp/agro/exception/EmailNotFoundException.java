package com.jsp.agro.exception;

public class EmailNotFoundException extends RuntimeException{
	private String message="Email Not found";

	public String getMessage() {
		return message;
	}

	public EmailNotFoundException(String message) {
		super();
		this.message = message;
	}

	public EmailNotFoundException() {
		super();
	}
	
}
