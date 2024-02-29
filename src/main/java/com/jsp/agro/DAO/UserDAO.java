package com.jsp.agro.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public User updateUser(User user) {
		return repo.save(user);
	}
	public  void deleteUser(int id) {
		repo.deleteById(id);
	}
	
}
