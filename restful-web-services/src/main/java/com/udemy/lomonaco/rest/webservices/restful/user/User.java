package com.udemy.lomonaco.rest.webservices.restful.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Detalhes sobre o usuario")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min = 2, message = "Nome deve ter ao menos 2 caracteres")
	@ApiModelProperty(notes = "Nome deve ter ao menos 2 caracteres")
	private String name;
	@Past
	@ApiModelProperty(notes = "data deve estar no passado")
	private Date birthDate;

	protected User() {
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
