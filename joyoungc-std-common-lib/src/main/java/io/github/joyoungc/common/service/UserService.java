package io.github.joyoungc.common.service;

import java.util.List;

import io.github.joyoungc.common.model.User;

public interface UserService {
	
	User getUser(String userId);
	
	List<User> selectUsers();
	
	User createUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(String userId);

}
