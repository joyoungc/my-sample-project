package io.github.joyoungc.security.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.joyoungc.jpa.user.repository.UserRepository;

@Controller
public class AuthController {
	
	@Autowired
	UserRepository repository;
	
	@GetMapping(value = { "/home", "/" })
	public String home() {
		return "home";
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value = { "/pwa"})
	public String pwa() {
		return "pwa";
	}
	
/*	@GetMapping("/api/users")
	@ResponseBody
	public List<User> selectUsers() {
		return repository.findAll();
	}*/

}
