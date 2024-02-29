package com.jsp.agro.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.User;
import com.jsp.agro.service.UserService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
		return service.SaveUser(user);
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String email,@RequestParam String password){
		return service.loginUser(email,password);
	}
	@GetMapping("/fetchbyid")
	public ResponseEntity<ResponseStructure<User>> fetchById(@RequestParam int id){
		return service.fetchById(id);
	}
	@GetMapping("/fetchall")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		return service.fetchAll();
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id){
		return service.deleteUser(id);
	}
	@PutMapping("/sendotp")
	public ResponseEntity<ResponseStructure<Integer>> sendOTP(String email){
		return service.mailOTP(email);
	}
}
