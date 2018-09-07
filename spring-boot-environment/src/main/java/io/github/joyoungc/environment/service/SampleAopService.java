package io.github.joyoungc.environment.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class SampleAopService {
	
	public String hello(String input, HttpSession session) {
		return "hello aop";
	}

}
