package com.udemy.lomonaco.rest.webservices.restful.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.udemy.lomonaco.rest.webservices.restful.user.User;

@Component
public class PostDaoService {

	private static List<Post> posts = new ArrayList<>();
	private int postsCount = 3;

	static {
		posts.add(new Post(1, "Primeiro texto do usuario 1", new User(1, "Fernando", new Date())));
		posts.add(new Post(2, "Primeiro texto do usuario 2", new User(2, "Jao", new Date())));
		posts.add(new Post(3, "Primeiro texto do usuario 3", new User(3, "Paulo", new Date())));
	}

	public List<Post> findAll() {
		return posts;
	}

	public Post save(int userId, Post post) {
		if (post.getId() == null) {
			post.setId(++postsCount);
		}
		posts.add(post);
		return post;
	}

	public List<Post> findAllPostOfUser(int userId) {
		List<Post> postUser = new ArrayList<>();
		for (Post post : posts) {
			if (post.getUser().getId() == userId) {
				postUser.add(post);
			}
		}
		return postUser;
	}

	public Post findByIdAndUserId(int id, int userId) {
		for (Post post : posts) {
			if (post.getId() == id && post.getUser().getId() == userId) {
				return post;
			}
		}
		return null;
	}

}
