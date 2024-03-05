package com.jsp.agro.DAO;

import java.util.Optional;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.ImageRepo;
import com.jsp.agro.repo.UserRepo;

@Repository
public class ImageDAO {
	@Autowired
	ImageRepo repo;
	@Autowired
	UserDAO udao;
	
	public Image uploadImage(Image image){
		return repo.save(image);
	}
	public Image updateImage(Image image){
		return repo.save(image);
	}
	public Image fetchImageById(int id){
		Optional<Image> image = repo.findById(id);
		if(image.isEmpty()) {
			return null;
		}
		else {
			return image.get();
		}
	}
	public void deleteImage(Image image){
		User img = udao.findByImage(image);
		if(img!=null) {
			img.setImage(null);
			udao.updateUser(img);
		}
		repo.delete(image);
	}
	
}
