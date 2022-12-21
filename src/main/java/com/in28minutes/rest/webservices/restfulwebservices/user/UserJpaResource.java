package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	private UserDaoService service;
	
	private UserRepository repository;
	
	public UserJpaResource( UserRepository repository) {
		this.repository=repository;
	}
	
	//GET All Users
	@GetMapping("/jpa/Users")
	public List<User> retreiveAll(){
		return repository.findAll();
	}
	
	//GET One User
//	@GetMapping("/jpa/Users/{id}")
//	public User retreiveAll(@PathVariable int id){
//		Optional<User> user = repository.findById(id);
//		if(user.isEmpty()) 
//			throw new UserNotFoundException("id:" +id);
//		EntityModel<User> entityModel = EntityModel.of(user.get());
//		
//		WebMvcLinkBuilder link= linkTo(methodOn)(this.getClass()).retreiveAll());
//		entityModel.add(link.withRel("all-users");)
//		return en;
//	}
	
	//Delete One User
	@DeleteMapping("/jpa/Users/{id}")
	public void deleteUser(@PathVariable int id){
		 repository.deleteById(id);
	}
	
	//POST users
//	@PostMapping("/Users")
//	public ResponseEntity<User> createUser(@RequestBody User user) {
//		service.save(user);
//		return ResponseEntity.created(null).build(); 	
//		
//	}
	
	// Post Mapping using Location- header
	@PostMapping("/jpa/Users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUser = repository.save(user);
		//to get URI User/4 => /Users, append {id} => getId
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
}
