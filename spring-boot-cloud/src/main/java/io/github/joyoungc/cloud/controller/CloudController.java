package io.github.joyoungc.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello Sleuth";
	}

}
