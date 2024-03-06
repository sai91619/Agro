package com.jsp.agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.DAO.UserDAO;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.EmailNotFoundException;
import com.jsp.agro.exception.IncorrectPasswordException;
import com.jsp.agro.exception.UserAlreadyExistException;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class UserService {
	@Autowired 
	UserDAO dao;
	@Autowired
	JavaMailSender javaMailSender;
	
	ResponseStructure<User> rs= new ResponseStructure<User>();
	public ResponseEntity<ResponseStructure<User>> SaveUser(User user) {
		User u= dao.findByEmail(user.getEmail());
		if(u==null) {
			dao.SaveUser(user);
			SimpleMailMessage message=new SimpleMailMessage();
			message.setFrom("saikiran.nagineni@gmail.com");
			message.setTo(user.getEmail());
			message.setSubject("Account Creation Confirmation");
			message.setText("Dear "+user.getFirstName()+"\n"
					+ "\r\n"
					+ "Congratulations! Your account with Agro has been successfully created. Welcome to our platform!\r\n"
					+ "\r\n"
					+ "Below are your account details:\r\n"
					+ "\r\n"
					+ "Email Address: "+user.getEmail()+"\r\n"
					+ "Password:"+user.getPassword());
			javaMailSender.send(message);
			rs.setMessage("Registration successfull");
			rs.setStatus(HttpStatus.CREATED.value());
			rs.setData(user);
			return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.CREATED);
		}
		else {
			throw new UserAlreadyExistException("User already exist with the email Id "+user.getEmail());
		}
	}
	public ResponseEntity<ResponseStructure<User>> loginUser(String email, String password) {
		User u=dao.findByEmail(email);
		if(u==null) {
			throw new EmailNotFoundException(email+" is not found");
		}
		else {
			if(u.getPassword().equals(password)) {
				rs.setMessage("Login Successfull");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				rs.setData(u);
				return new ResponseEntity<ResponseStructure<User>>(rs,HttpStatus.ACCEPTED);
			}
			else {
				throw new IncorrectPasswordException("Password entered is incorrect");
			}
		}
	}
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll() {
		ResponseStructure<List<User>> rsl=new ResponseStructure<List<User>>();
		rsl.setMessage("Fetch Successfull");
		rsl.setStatus(HttpStatus.ACCEPTED.value());
		rsl.setData(dao.findAll());
		return new ResponseEntity<ResponseStructure<List<User>>>(rsl,HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<User>> fetchById(int id) {
		User u= dao.findById(id);
		if(u!=null) {
			rs.setMessage("Fetch Successfull");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			rs.setData(u);
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.ACCEPTED);
		}
		else {
			throw new UserNotFoundException("User with "+id+" is not exist");
		}
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		User u=dao.updateUser(user);
		rs.setMessage("Update Successfull");
		rs.setStatus(HttpStatus.ACCEPTED.value());
		rs.setData(u);
		return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.ACCEPTED);
		
	}
	public  ResponseEntity<ResponseStructure<User>> deleteUser(int id) {
		User u= dao.findById(id);
		if(u!=null) {
			dao.deleteUser(id);
			rs.setMessage("User deleted successfull");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			rs.setData(u);
			return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.ACCEPTED);
		}
		else {
			throw new UserNotFoundException("User with "+id+" is not exist");
		}
	}
	public ResponseEntity<ResponseStructure<Integer>> mailOTP(String email) {
		SimpleMailMessage message= new SimpleMailMessage();
		User u=dao.findByEmail(email);
		if(u!=null) {
			message.setFrom("saikiran.nagineni@gmail.com");
			message.setTo(email);
			message.setSubject("OTP to change Password");
			message.setText("OTP to change your password is : "+((int)(Math.random()*900000+100000)));
			javaMailSender.send(message);
			ResponseStructure<Integer> s=new ResponseStructure<Integer>();
			s.setMessage("OTP Sent successfully to: "+email);
			s.setStatus(HttpStatus.ACCEPTED.value());
			s.setData(null);
			return new ResponseEntity<ResponseStructure<Integer>>(s,HttpStatus.ACCEPTED);
		}
		else {
			throw new EmailNotFoundException(email+ "Not found");
		}
	}
}
