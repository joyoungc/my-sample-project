package io.github.joyoungc.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import io.github.joyoungc.netty.server.NettyServer;

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.joyoungc"})
public class SpringBootNettyApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootNettyApplication.class, args);
		NettyServer nettyServer = context.getBean(NettyServer.class);
		nettyServer.start();
	}
	
}
