package io.github.joyoungc.common.exception;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ConfigurationProperties("application.exception")
public class ExceptionProperties {
	
	private String defaultErrorCode;

}
