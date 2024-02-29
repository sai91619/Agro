package com.jsp.agro.exception;

public class UserNotFoundException extends RuntimeException{
	private String message="User Not Found";

	public String getMessage() {
		return message;
	}

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	public UserNotFoundException() {
		super();
	}
}
