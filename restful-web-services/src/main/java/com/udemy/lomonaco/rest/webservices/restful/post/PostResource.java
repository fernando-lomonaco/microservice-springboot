package com.udemy.lomonaco.rest.webservices.restful.post;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Endpoint posts")
@RestController
public class PostResource {

	@Autowired
	private PostDaoService service;

	@GetMapping(path = "/users/{userId}/posts/{id}")
	@ApiOperation(value = "Post do usuario")
	public Post postRetrieve(@PathVariable int userId, @PathVariable int id) {
		Post post = service.findByIdAndUserId(id, userId);
		if (post == null) {
			throw new PostNotFoundException("userid-" + userId);
		}
		return service.findByIdAndUserId(id, userId);
	}

	@GetMapping(path = "/users/{userId}/posts")
	@ApiOperation(value = "Lista de todos post do usuario")
	public List<Post> postRetrieveOfUser(@PathVariable int userId) {
		List<Post> post = service.findAllPostOfUser(userId);
		if (post.isEmpty()) {
			throw new PostNotFoundException("userid-" + userId);
		}
		return service.findAllPostOfUser(userId);
	}

	@PostMapping(path = "/users/{userId}/posts")
	public ResponseEntity<Post> savePostOfUser(@RequestBody Post post, @PathVariable int userId) {
		Post savedUser = service.save(userId, post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // atual request
				.path("/{id}") // pegar o id
				.buildAndExpand(savedUser.getId()) // valor
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
