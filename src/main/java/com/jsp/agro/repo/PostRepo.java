package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro.entity.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

}
