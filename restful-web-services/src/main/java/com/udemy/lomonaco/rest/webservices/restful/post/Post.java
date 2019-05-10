package com.udemy.lomonaco.rest.webservices.restful.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udemy.lomonaco.rest.webservices.restful.user.User;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Detalhes sobre os posts")
@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(max = 256, message = "Texto deve ter no maximo 256 caracteres")
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	protected Post() {
	}

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
		return "Post [id=" + id + ", text=" + text + "]";
	}

}
