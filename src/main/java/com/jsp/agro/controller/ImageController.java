package com.jsp.agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.entity.Image;
import com.jsp.agro.service.ImageService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class ImageController {
	@Autowired
	ImageService service;
	
	@PostMapping("/uploadimage")
	public ResponseEntity<ResponseStructure<Image>> uploadImage(@RequestParam int id,@RequestParam MultipartFile file) throws IOException{
		return service.uploadImage(id,file);
	}
	@PutMapping("/updateimage")
	public ResponseEntity<ResponseStructure<Image>> updateImage(@RequestParam int id,@RequestParam MultipartFile file) throws IOException{
		return service.updateImage(id,file);
	}
	@GetMapping("/getimage")
	public ResponseEntity<ResponseStructure<Image>> fetchImage(@RequestParam int id){
		return service.fetchImageById(id);
	}
	@DeleteMapping("/deleteimage")
	public ResponseEntity<ResponseStructure<String>> deleteImage(@RequestParam int id){
		return service.deleteImageById(id);
	}
}
