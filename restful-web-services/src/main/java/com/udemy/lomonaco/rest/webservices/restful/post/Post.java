package com.udemy.lomonaco.rest.webservices.restful.post;

import com.udemy.lomonaco.rest.webservices.restful.user.User;

public class Post {

	private Integer id;
	private String text;
	private User user;

	public Post(Integer id, String text, User user) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", text=" + text + ", user=" + user + "]";
	}

}
