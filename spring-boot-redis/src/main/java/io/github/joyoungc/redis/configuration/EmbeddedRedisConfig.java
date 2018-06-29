package io.github.joyoungc.redis.configuration;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.embedded.RedisServer;


@Component
public class EmbeddedRedisConfig {
	
	@Value("${spring.redis.port}")
	private int redisPort;

	private RedisServer redisServer;
	
	@PostConstruct
	public void startRedis() throws IOException {
		redisServer = RedisServer.builder().port(6379).setting("maxmemory 32M").build();
		redisServer.start();
	}

	@PreDestroy
	public void stopRedis() {
		redisServer.stop();
	}

}
