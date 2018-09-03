package io.github.joyoungc.redis.configuration;

import lombok.Data;

@Data
public class RedisProperty {
	private String host;
	private int port;
	private int database;
}
