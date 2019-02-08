package io.github.joyoungc.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.joyoungc"})
public class SpringBootEnvApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEnvApplication.class, args);
    }
}
