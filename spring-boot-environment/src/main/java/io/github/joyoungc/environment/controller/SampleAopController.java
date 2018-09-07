package io.github.joyoungc.environment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.environment.service.SampleAopService;

@RestController
@RequestMapping("/aop")
public class SampleAopController {

	private final SampleAopService aopService;
	
	public SampleAopController(SampleAopService aopService) {
		this.aopService = aopService;
	}

	@GetMapping("/hello")
	public String hello(String input, HttpSession session) {
		return aopService.hello(input, session);
	}

}
