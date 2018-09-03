package io.github.joyoungc.redis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisRepositoryConfig {
	
	@Autowired
	SessionRedisProperty sessionProperty;
	
	@Autowired
	ApplicationRedisProperty applicationProperty;
	
	@Primary
	@Bean(name="sessionRedisConnectionFactory") 
	public RedisConnectionFactory sessionRedisConnectionFactory() {
		LettuceConnectionFactory factory = new LettuceConnectionFactory(sessionProperty.getHost(), sessionProperty.getPort());
		factory.setDatabase(sessionProperty.getDatabase());
		return factory;	
	}
	
	@Bean(name="applicationRedisConnectionFactory") 
	public RedisConnectionFactory applicationRedisConnectionFactory() {
		LettuceConnectionFactory factory = new LettuceConnectionFactory(applicationProperty.getHost(), applicationProperty.getPort());
		factory.setDatabase(applicationProperty.getDatabase());
		return factory;	
	}

	/**
	 * Spring Data Repository를 위한 restTemplate
	 */
	@Bean(name="redisTemplate")
	public RedisTemplate<?, ?> applicationRedisTemplate() {
		RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(applicationRedisConnectionFactory());
        return redisTemplate;
	}

}
