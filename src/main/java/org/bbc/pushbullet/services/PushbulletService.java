package org.bbc.pushbullet.services;

import java.util.Arrays;
import java.util.Map;

import org.bbc.pushbullet.handlers.MyResponseErrorHandler;
import org.bbc.pushbullet.items.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PushbulletService {
	
	private final String url = "https://api.pushbullet.com/v2";
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> sendNoteNotification(User user,String msg, String title) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new MyResponseErrorHandler());
		MultiValueMap<String,String> message = new LinkedMultiValueMap<>();
		message.add("body", msg);
		message.add("title", title);
		message.add("type", "note");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Access-Token", user.getAccessToken());
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(message,headers);
		ResponseEntity<JsonNode> response = null;
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = null;
		try {
			response = restTemplate.exchange(url+ "/pushes", HttpMethod.POST, entity, JsonNode.class);
			user.increamentPushNotification();
			result = mapper.convertValue(response, Map.class);
			
		}catch (HttpStatusCodeException exception) {
		    int statusCode = exception.getStatusCode().value();
		    
		}
		
		System.out.println(result);
		
		return result;
	}
	

}
