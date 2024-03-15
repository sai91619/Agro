package com.jsp.agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.Comment;
import com.jsp.agro.service.CommentService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@PostMapping("/saveComment")
	public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestParam int postid,@RequestParam int userid, @RequestParam String message) {
		return service.saveComment(postid, userid, message);
	}
	
	@DeleteMapping("/deleteComment")
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(@RequestParam int id){
		return service.deleteComment(id);
	}
}
