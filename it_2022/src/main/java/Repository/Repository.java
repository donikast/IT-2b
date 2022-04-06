package Repository;

import java.util.HashSet;
import java.util.Set;

import models.User;

public class Repository {
	
	private static Repository instance = null;
	private static Set<User> collection;
	static int index=1;
	
	private Repository() {}
	
	public static Repository getInstance() {
		if(instance == null) {
			instance = new Repository();
			collection = new HashSet<User>();
		}
		return instance;
	}
	
	public void addUser(User user) {
		user.setId(index++);
		collection.add(user);
	}
	
	public boolean ifExist(User user) {
		return collection.contains(user);
	}
	
	public User getUserByUsername(String username) {
		for(User u:collection) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	public User getUserById(int id) {
		for(User u:collection) {
			if(u.getId()==id) {
				return u;
			}
		}
		return null;
	}

}
