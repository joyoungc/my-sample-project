package io.github.joyoungc.common.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface ExceptionResponse<T extends Throwable> {

	ResponseEntity<Object> handle(T ex, HttpServletRequest request, HttpServletResponse response);
	
	boolean isSupport(T ex, HttpServletRequest request, HttpServletResponse response);

}
