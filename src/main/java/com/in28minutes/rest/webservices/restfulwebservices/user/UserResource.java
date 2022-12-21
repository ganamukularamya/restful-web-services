package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	//GET All Users
	@GetMapping("/Users")
	public List<User> retreiveAll(){
		return service.findAll();
	}
	
	//GET One User
	@GetMapping("/Users/{id}")
	public User retreiveAll(@PathVariable int id){
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id:" +id);
		}
		return user;
	}
	
	//Delete One User
	@DeleteMapping("/Users/{id}")
	public void deleteUser(@PathVariable int id){
		 service.deleteById(id);
	}
	
	//POST users
//	@PostMapping("/Users")
//	public ResponseEntity<User> createUser(@RequestBody User user) {
//		service.save(user);
//		return ResponseEntity.created(null).build(); 	
//		
//	}
	
	// Post Mapping using Location- header
	@PostMapping("/Users")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser = service.save(user);
		//to get URI User/4 => /Users, append {id} => getId
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
}
