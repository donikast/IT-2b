package Repository;

import java.util.HashSet;
import java.util.Set;

import models.User;

public class Repository {
	
	private static Repository instance = null;
	private static Set<User> collection;
	
	private Repository() {}
	
	public static Repository getInstance() {
		if(instance == null) {
			instance = new Repository();
			collection = new HashSet<User>();
		}
		return instance;
	}
	
	public boolean addUser(User user) {
		return collection.add(user);
	}

}
