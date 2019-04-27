package com.udemy.lomonaco.rest.webservices.restful.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Endpoint usuario")
@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping(path = "/users")
	@ApiOperation(value = "Lista todos usuarios", response = User[].class)
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping(path = "/users/{id}")
	@ApiOperation(value = "Informacao do usuario", response = User.class)
	public Resource<User> userRetrieve(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}

		Resource<User> resource = new Resource<>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping(path = "/users")
	@ApiOperation(value = "Criar novo usuario", response = User.class)
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // atual request
				.path("/{id}") // pegar o id
				.buildAndExpand(savedUser.getId()) // valor
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(path = "/users/{id}")
	@ApiOperation(value = "Remover do usuario")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}

}
