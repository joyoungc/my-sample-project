package io.github.joyoungc.redis.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis.application")
public class ApplicationRedisProperty extends RedisProperty {
}
