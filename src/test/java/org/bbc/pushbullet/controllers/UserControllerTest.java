package org.bbc.pushbullet.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void registeringUserShouldReturnRegistredUser() throws Exception {	
		this.mockMvc.perform(post("/register").param("username", "biniam").param("accessToken", "token"))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void shouldReturnAllRegisteredUsers() throws Exception {
		this.mockMvc.perform(get("/users"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void givenUsernameAndMessageExpectedToPushNotificationSuccesfully() throws Exception {
		this.mockMvc.perform(post("/pushNotification").param("username", "biniam").param("message", "hello there"))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
}
