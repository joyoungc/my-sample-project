package io.github.joyoungc.jpa.user.service;

import org.springframework.stereotype.Service;

import io.github.joyoungc.jpa.user.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

}
