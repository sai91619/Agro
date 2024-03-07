package com.jsp.agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.entity.Post;
import com.jsp.agro.service.PostService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class PostController {
	@Autowired
	PostService service;
	
	@PostMapping("/uploadpost")
	public ResponseEntity<ResponseStructure<Post>> uploadPost(@RequestParam int userId, @RequestParam MultipartFile file, @RequestParam String caption, @RequestParam String location) throws IOException {
		return service.savePost(userId, file, caption, location);
	}
	@GetMapping("/fetchpost")
	public ResponseEntity<ResponseStructure<Post>> fetchPost(@RequestParam int id){
		return service.fetchPostById(id);
	}
}
