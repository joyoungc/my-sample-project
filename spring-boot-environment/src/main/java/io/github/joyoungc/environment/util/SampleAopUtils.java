package io.github.joyoungc.environment.util;

import org.springframework.stereotype.Component;

@Component
public class SampleAopUtils {
	
	public void executeEcho(String input) {
		System.out.println(input);
	}

}
