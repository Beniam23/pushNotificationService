package org.bbc.pushbullet.services;

import java.util.ArrayList;
import java.util.List;
import org.bbc.pushbullet.items.User;

/**
 * @author biniamgebreyesus
 * @since 25/09/17
 * @version 1.0
 * */
public class UserService {

	List<User> users = new ArrayList<>();

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void addUser(User user) {
		this.users.add(user);
	}
}
