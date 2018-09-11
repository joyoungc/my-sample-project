package io.github.joyoungc.environment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.joyoungc.environment.service.SampleAopService;

@RestController
@RequestMapping("/aop")
public class SampleAopController {

	private final SampleAopService aopService;
	
	@Autowired
	MessageSource messageSource;
	
	public SampleAopController(SampleAopService aopService) {
		this.aopService = aopService;
	}

	@GetMapping("/hello")
	public String hello(String input, HttpSession session) {
		String message = messageSource.getMessage("test", null, LocaleContextHolder.getLocale());
		System.out.println(message);
		return aopService.hello(input, session);
	}

}
