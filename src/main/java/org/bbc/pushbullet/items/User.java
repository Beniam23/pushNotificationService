package org.bbc.pushbullet.items;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author biniamgebreyesus
 * @since 25/09/17
 * @version 1.0
 * */
public class User {

	private String username;
	private String accessToken;
	private String creationDate;
	private int numOfNotificationsPushed;
	
	/**
	 * @param username
	 * @param accessToken
	 */
	public User(String username, String accessToken) {
		super();
		this.username = username;
		this.accessToken = accessToken;
		this.creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the numOfNotificationsPushed
	 */
	public int getNumOfNotificationsPushed() {
		return numOfNotificationsPushed;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", accessToken=" + accessToken + ", creationDate=" + creationDate
				+ ", numOfNotificationsPushed=" + numOfNotificationsPushed + "]";
	}

	public void increamentPushNotification() {
		this.numOfNotificationsPushed++;
	}
	
}
