package com.udemy.lomonaco.rest.webservices.restful.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.lomonaco.rest.webservices.restful.post.Post;
import com.udemy.lomonaco.rest.webservices.restful.post.PostRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Endpoint usuario")
@RequestMapping("/jpa")
@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping(path = "/users")
	@ApiOperation(value = "Lista todos usuarios", response = User[].class)
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/users/{id}")
	@ApiOperation(value = "Informacao do usuario", response = User.class)
	public Resource<User> userRetrieve(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		Resource<User> resource = new Resource<>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping(path = "/users")
	@ApiOperation(value = "Criar novo usuario", response = User.class)
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // atual request
				.path("/{id}") // pegar o id
				.buildAndExpand(savedUser.getId()) // valor
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(path = "/users/{id}")
	@ApiOperation(value = "Remover do usuario")
	public void deleteUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		userRepository.delete(user.get());
	}

	@GetMapping(path = "/users/{id}/posts")
	@ApiOperation(value = "Lista todos posts do usuarios")
	public List<Post> retrieveAllPostsOfUsers(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		return userOptional.get().getPosts();

	}

	@PostMapping(path = "/users/{id}/posts")
	@ApiOperation(value = "Criar novo post do usuario")
	public ResponseEntity<User> savePostOfUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		User user = userOptional.get();

		post.setUser(user);
		postRepository.save(post);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // atual request
				.path("/{id}") // pegar o id
				.buildAndExpand(post.getId()) // valor
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
