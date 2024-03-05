package com.jsp.agro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.agro.exception.EmailNotFoundException;
import com.jsp.agro.exception.ImageNotFoundException;
import com.jsp.agro.exception.IncorrectPasswordException;
import com.jsp.agro.exception.UserAlreadyExistException;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandler {
	ResponseStructure<String> rs= new ResponseStructure<String>();

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> userExist(UserAlreadyExistException e) {
		rs.setMessage("User Already exist");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFound(EmailNotFoundException e) {
		rs.setMessage("Email Not Found");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> incorrectPassword(IncorrectPasswordException e) {
		rs.setMessage("Password Incorrect");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> invalidUser(UserNotFoundException e) {
		rs.setMessage("Invalid User");
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ImageNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> invalidImage(ImageNotFoundException e) {
		rs.setMessage("Invalid Id");
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
}
