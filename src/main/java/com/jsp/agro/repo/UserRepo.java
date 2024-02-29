package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	@Query("select e from User e where e.email=?1")
	User findByEmail(String email);
}
