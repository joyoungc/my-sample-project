package io.github.joyoungc.redis.configuration;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

@Slf4j
@Component
public class EmbeddedRedisConfig {
	
	@Value("${spring.redis.port}")
	private int redisPort;

	private RedisServer redisServer;
	
	@PostConstruct
	public void startRedis() throws IOException {
		redisServer = RedisServer.builder().port(redisPort).setting("maxmemory 32M").build();
		redisServer.start();
		log.info("## Embedded Redis Start!!!");
	}

	@PreDestroy
	public void stopRedis() {
		redisServer.stop();
		log.info("## Embedded Redis Stop...");
	}

}
