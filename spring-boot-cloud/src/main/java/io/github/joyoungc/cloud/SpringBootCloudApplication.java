package io.github.joyoungc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.joyoungc"})
public class SpringBootCloudApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCloudApplication.class, args);
	}

}
