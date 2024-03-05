package com.jsp.agro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.DAO.ImageDAO;
import com.jsp.agro.DAO.UserDAO;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.ImageNotFoundException;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class ImageService {
	@Autowired
	ImageDAO dao;
	@Autowired
	UserDAO udao;
	ResponseStructure<Image> rs= new ResponseStructure<Image>();
	public ResponseEntity<ResponseStructure<Image>> uploadImage(int id, MultipartFile file) throws IOException{
		User user=udao.findById(id);
		if(user!=null) {
			if(user.getImage()!=null) {
				return updateImage(user.getImage().getId(), file);
			}
			Image image=new Image();
			image.setName(file.getOriginalFilename());
			image.setData(file.getBytes());
			user.setImage(image);
			udao.updateUser(user);
			rs.setMessage("Image uploaded successfully");
			rs.setStatus(HttpStatus.ACCEPTED.value());
			rs.setData(image);
			return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.ACCEPTED);
		}
		else {
			throw new UserNotFoundException("User not found with the Id: "+id);
		}
	}
	public ResponseEntity<ResponseStructure<Image>> updateImage(int id, MultipartFile file) throws IOException{
		Image image=dao.fetchImageById(id);
		image.setId(id);
		image.setName(file.getOriginalFilename());
		image.setData(file.getBytes());
		dao.updateImage(image);
		rs.setMessage("Image updated successfully");
		rs.setStatus(HttpStatus.ACCEPTED.value());
		rs.setData(image);
		return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<Image>> fetchImageById(int id){
		Image image=dao.fetchImageById(id);
		if(image!=null) {
			rs.setMessage("Image fetched successfully");
			rs.setStatus(HttpStatus.FOUND.value());
			rs.setData(image);
			return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.FOUND);
		}
		else {
			throw new ImageNotFoundException("Image not found with id: "+id);
		}
	}
	public ResponseEntity<ResponseStructure<String>> deleteImageById(int id){
		ResponseStructure<String> rss= new ResponseStructure<String>();
		Image image=dao.fetchImageById(id);
		if(image!=null) {
			dao.deleteImage(image);
			rss.setMessage("Image deleted successfully");
			rss.setStatus(HttpStatus.FOUND.value());
			rss.setData(image.getName()+" Deleted Successfully");
			return new ResponseEntity<ResponseStructure<String>>(rss,HttpStatus.FOUND);
		}
		else {
			throw new ImageNotFoundException("Image not found with id: "+id);
		}
	}
}
