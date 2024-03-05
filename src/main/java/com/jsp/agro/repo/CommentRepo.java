package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
