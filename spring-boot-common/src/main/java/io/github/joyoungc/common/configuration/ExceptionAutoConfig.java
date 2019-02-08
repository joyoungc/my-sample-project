package io.github.joyoungc.common.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.joyoungc.common.exception.ExceptionProperties;
import io.github.joyoungc.common.exception.handler.AbstractExceptionResponse;
import io.github.joyoungc.common.exception.handler.DefaultExceptionResponse;
import io.github.joyoungc.common.exception.handler.RestTemplateExceptionResponse;
import io.github.joyoungc.common.handler.RequestBodyCachingFilter;

@Configuration
@EnableConfigurationProperties(ExceptionProperties.class)
public class ExceptionAutoConfig {

    @Bean
    public AbstractExceptionResponse restTemplateExceptionResponse() {
        return new RestTemplateExceptionResponse();
    }

    @Bean
    public AbstractExceptionResponse defaultExceptionResponse() {
        return new DefaultExceptionResponse();
    }

    @Bean
    public RequestBodyCachingFilter requestBodyCachingFilter() {
        return new RequestBodyCachingFilter();
    }

}
