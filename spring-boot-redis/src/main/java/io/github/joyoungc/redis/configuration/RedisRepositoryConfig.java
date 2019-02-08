package io.github.joyoungc.redis.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisRepositoryConfig {

    @Primary
    @Bean(name = "sessionRedisConnectionFactory")
    public RedisConnectionFactory sessionRedisConnectionFactory(
            @Qualifier("sessionProperties") RedisProperties properties) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(properties.getHost(), properties.getPort());
        factory.setDatabase(properties.getDatabase());
        return factory;
    }

    @Bean(name = "applicationRedisConnectionFactory")
    public RedisConnectionFactory applicationRedisConnectionFactory(
            @Qualifier("applicationProperties") RedisProperties properties) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(properties.getHost(), properties.getPort());
        factory.setDatabase(properties.getDatabase());
        return factory;
    }

    /**
     * Spring Data Repository를 위한 restTemplate
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<?, ?> applicationRedisTemplate(
            @Qualifier("applicationRedisConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean(name = "sessionProperties")
    @ConfigurationProperties(prefix = "spring.redis.session")
    public RedisProperties sessionProperties() {
        return new RedisProperties();
    }

    @Bean(name = "applicationProperties")
    @ConfigurationProperties(prefix = "spring.redis.application")
    public RedisProperties applicationProperties() {
        return new RedisProperties();
    }

}
