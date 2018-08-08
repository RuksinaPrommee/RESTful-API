package com.example.helloworld;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
	private static final Map<Integer, User> userMap = new HashMap<Integer, User>();
	
	public static User addUser(User user) {
		userMap.put(user.getId(), user);
        return user;
    }
	
	public static User updateUser(User user) {
		userMap.put(user.getId(), user);
        return user;
    }


}
