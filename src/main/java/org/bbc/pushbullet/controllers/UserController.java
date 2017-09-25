package org.bbc.pushbullet.controllers;

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
	
}
