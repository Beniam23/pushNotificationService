package org.bbc.pushbullet.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.bbc.pushbullet.items.User;
import org.bbc.pushbullet.services.PushbulletService;
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
	public ResponseEntity<User> register(@RequestParam(value="username") String username,
										@RequestParam(value="accessToken") String accessToken){
		
		Optional<User> users = userService.getUsers()
				.stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst();		
		
		if(users.isPresent())
			return new ResponseEntity<User>(users.get(), HttpStatus.ALREADY_REPORTED);
		
		User user = new User(username,accessToken);
		userService.addUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/pushNotification", method=RequestMethod.POST)
	public ResponseEntity<Map<String , Object>> sendPushNotification(@RequestParam(value="username") String username,
																	@RequestParam(value="message") String message){
		
		Map<String , Object> response = null;
		Optional<User> user = userService.getUsers()
				.stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst();
	
		if(user.isPresent())  
			response = new PushbulletService().sendNoteNotification(user.get() , message, "PushNotification");
		 
		
		return new ResponseEntity<Map<String , Object>>(response,(response.get("statusCode").equals("OK"))?HttpStatus.OK : HttpStatus.UNAUTHORIZED);
	}
}
