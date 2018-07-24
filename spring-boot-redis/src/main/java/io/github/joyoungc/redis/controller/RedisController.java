package io.github.joyoungc.redis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
	
	@GetMapping("/session")
	public String getHello(HttpSession session) {
		if(session.isNew()) {
			session.setAttribute("test", "테스트");
		}
		return "Hello Session!";
	}

}
