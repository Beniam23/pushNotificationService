package org.bbc.pushbullet.controllers;

import java.util.List;

import org.bbc.pushbullet.items.User;
import org.bbc.pushbullet.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	UserService userService = new UserService();
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ResponseEntity<User> register(
			@RequestParam(value="username") String username,
			@RequestParam(value="accessToken") String accessToken){
		
		User user = new User(username,accessToken);
		userService.addUser(user);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		userService.addUser(new User("biniam", "token 1"));
		userService.addUser(new User("sami", "token 2"));
		userService.addUser(new User("liz", "token 3"));
		
		
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}
	
}
