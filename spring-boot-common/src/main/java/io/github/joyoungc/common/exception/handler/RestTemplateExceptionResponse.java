package io.github.joyoungc.common.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public class RestTemplateExceptionResponse extends AbstractExceptionResponse {

    @Override
    public String getErrorCode(Throwable ex, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(messageSource.getMessage("hello", null, locale));
        return super.getErrorCode(ex, request, response);
    }

    @Override
    public ResponseEntity<Object> handle(Throwable ex, HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isSupport(Throwable ex, HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return false;
    }


}
