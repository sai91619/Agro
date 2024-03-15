package com.jsp.agro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.CommentDao;
import com.jsp.agro.dao.PostDao;
import com.jsp.agro.dao.UserDao;
import com.jsp.agro.entity.Comment;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.CommentNotFoundException;
import com.jsp.agro.exception.IdNotFoundException;
import com.jsp.agro.exception.PostNotFoundException;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class CommentService {

	@Autowired
	private CommentDao cdao;

	@Autowired
	private PostDao pdao;

	@Autowired
	private UserDao udao;

	ResponseStructure<Comment> rs = new ResponseStructure<Comment>();

	public ResponseEntity<ResponseStructure<Comment>> saveComment(int postid, int userid, String message) {
		Post post = pdao.fetchById(postid);
		if (post != null) {
			User user = udao.fetchById(userid);
			if (user != null) {
				Comment c = new Comment();
				c.setComment(message);
				c.setUser(user);
				cdao.saveComment(c);
				List<Comment> clist = new ArrayList<Comment>();
				clist.add(c);
				clist.addAll(post.getComments());
				post.setComments(clist);
				pdao.updatePost(post);
				rs.setMessage("Commented succesfully");
				rs.setStatus(HttpStatus.OK.value());
				rs.setData(c);
				return new ResponseEntity<ResponseStructure<Comment>>(rs, HttpStatus.OK);
			} else {
				throw new UserNotFoundException();
			}
		} else
			throw new PostNotFoundException();
	}

//	public ResponseEntity<ResponseStructure<Comment>> deleteComment(int id){
//		Comment cdata = cdao.fetchById(id);
//		if(cdata!=null) {
//				Post pdata = pdao.fetchPostByComment(id);
//				if(pdata!=null) {
//					pdata.setComments(null);
//					pdao.updatePost(pdata);
//				}
//			cdao.deleteComment(cdata.getId());
//			rs.setData(cdata);
//			rs.setMessage("Comment deleted");
//			rs.setStatus(HttpStatus.FOUND.value());
//			return new ResponseEntity<ResponseStructure<Comment>>(rs,HttpStatus.FOUND);
//		}
//		else
//			throw new CommentNotFoundException();
//	}

	public ResponseEntity<ResponseStructure<Comment>> deleteComment(int id) {
		Comment db = cdao.fetchById(id);
		if (db != null) {
			List<Post> posts = pdao.fetchAll();
			for (Post p : posts) {
				List<Comment> com = p.getComments();
				if (com.contains(db)) {
					com.remove(db);
					pdao.updatePost(p);
					cdao.deleteComment(id);
					break;
				}
			}
			ResponseStructure<Comment> rs = new ResponseStructure<Comment>();
			rs.setData(db);
			;
			rs.setMessage("comment deleted successfully...");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Comment>>(rs, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Id not found with in the exist database :" + id);
		}

	}
}
