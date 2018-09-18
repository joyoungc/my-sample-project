package io.github.joyoungc.web.service;

import org.springframework.stereotype.Service;

@Service
public class ExceptionSampleService {
	
	public void throwsException()  {
		throw new RuntimeException();
	}

}
