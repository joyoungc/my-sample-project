package io.github.joyoungc.redis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RedisController {
	
	@GetMapping("/session")
	public String getHello(HttpSession session) {
		if(session.isNew()) {
			log.info("## Session is New!!");
			session.setAttribute("test", "테스트");
			return "Session is New!!";
		} else {
			log.info("## Session found~~~");
			return "Session found";
		}
	}

}
