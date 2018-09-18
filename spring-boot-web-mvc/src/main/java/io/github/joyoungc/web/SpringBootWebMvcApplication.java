package io.github.joyoungc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.joyoungc"})
public class SpringBootWebMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebMvcApplication.class, args);
	}
}
