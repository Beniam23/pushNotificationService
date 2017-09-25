package org.bbc.pushbullet.services;

import static org.junit.Assert.*;

import java.util.Map;

import org.bbc.pushbullet.items.User;
import org.junit.Test;

public class PushbulletServiceTest {

	@Test
	public void givenUserExpectedToPushNotificationSuccesfully() {
		User user = new User("biniam","o.xQrCMowPMN82rxbXV6VbRebDds6LQxkI");
		int notificationNum = user.getNumOfNotificationsPushed();
		Map<String, Object> response = new PushbulletService().sendNoteNotification(user, "message from pushbulletService", "pushbullet");

		assertEquals("OK",response.get("statusCode"));
		assertEquals(user.getNumOfNotificationsPushed(),notificationNum+1);
		
	}

}
