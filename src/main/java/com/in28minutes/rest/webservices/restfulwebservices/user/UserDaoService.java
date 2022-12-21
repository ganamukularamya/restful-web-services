package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component //Spring to manage this class
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount=0;
	
	static {
		users.add(new User(++usersCount,"Neymar",LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"Messi",LocalDate.now().minusYears(35)));
		users.add(new User(++usersCount,"Zlatan",LocalDate.now().minusYears(40)));
	}
	
	public List<User> findAll(){
		return users;
	}

	//GET One USer
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	//Delete By User
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
	
	//POST - adding a new user
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

}
