package com.jsp.agro.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.UserRepo;

@Repository
public class UserDAO {
	@Autowired
	UserRepo repo;
	public User SaveUser(User user) {
		return repo.save(user);
	}
	public User findById(int id) {
		Optional<User> x = repo.findById(id);
		if(x.isPresent())
			return x.get();
		return null;
	}
	public List<User> findAll() {
		return repo.findAll();
	}
	public User findByEmail(String email) {
		return repo.findByEmail(email);
	}
	public User findByImage(Image image) {
		return repo.findByImage(image);
	}
	public User updateUser(User user) {
		User u = findById(user.getId());
		if(user.getId()!=0) {
			u.setId(user.getId());
		}
		if(user.getFirstName()!=null) {
			u.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			u.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			u.setEmail(user.getEmail());
		}
		if(user.getPhone()!=0) {
			u.setPhone(user.getPhone());
		}
		if(user.getPassword()!=null) {
			u.setPassword(user.getPassword());
		}
		if(user.getAge()!=0) {
			u.setAge(user.getAge());
		}
		if(user.getGender()!=null) {
			u.setGender(user.getGender());
		}
		if(user.getAddress()!=null) {
			u.setAddress(user.getAddress());
		}
		if(user.getType()!=null) {
			u.setType(user.getType());
		}
		if(user.getImage()!=null) {
			u.setImage(user.getImage());
		}
		return repo.save(user);
	}
	public  void deleteUser(int id) {
		repo.deleteById(id);
	}
	
}
