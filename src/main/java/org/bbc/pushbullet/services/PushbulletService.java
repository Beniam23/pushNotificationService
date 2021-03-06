package org.bbc.pushbullet.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

/**
 * @author biniamgebreyesus
 * @since 25/09/17
 * @version 1.0
 *  
 *  this service provides a function to create a note notification */
public class PushbulletService {
	
	private final String url = "https://api.pushbullet.com/v2";
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> sendNoteNotification(User user,String msg, String title) {
		RestTemplate restTemplate = new RestTemplate();
		
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
		Map<String, Object> result = new HashMap<>();
		try {
			response = restTemplate.exchange(url+ "/pushes", HttpMethod.POST, entity, JsonNode.class);
			user.increamentPushNotification();
			result = mapper.convertValue(response, Map.class);
			
		}catch (HttpStatusCodeException exception) {
		    result.put("statusCode", exception.getStatusText());
		    result.put("statusCodeValue", exception.getStatusCode().value());
		    return result;
		}
		result.remove("headers");
		
		return result;
	}
	

}
