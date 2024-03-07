package com.jsp.agro.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Post;
import com.jsp.agro.repo.PostRepo;

@Repository
public class PostDAO {
	@Autowired
	PostRepo repo;
	public Post savePost(Post post){
		return repo.save(post);
	}
	public Post fetchPostById(int id) {
		Optional<Post> post = repo.findById(id);
		if(post.isEmpty()) {
			return null;
		}
		return post.get();
	}
}
