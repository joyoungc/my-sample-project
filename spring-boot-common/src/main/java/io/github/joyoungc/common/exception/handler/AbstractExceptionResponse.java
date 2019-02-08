package io.github.joyoungc.common.exception.handler;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;

import io.github.joyoungc.common.exception.ExceptionProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractExceptionResponse implements ExceptionResponse<Throwable> {

    @Autowired
    protected MessageSource messageSource; // 상속받는 exception response 객체들에서 공통으로 참조

    @Autowired
    protected ExceptionProperties exceptionProperties;  // 상속받는 exception response 객체들에서 공통으로 참조

    Locale locale = LocaleContextHolder.getLocale();

    protected String getErrorCode(final Throwable ex, final HttpServletRequest request,
                                  final HttpServletResponse response) {
        String errorCode = exceptionProperties.getDefaultErrorCode(); //default 값 ...
        return errorCode;
    }

    protected void makeErrorTraceLog(final ResponseEntity<Object> responseEntity, final Throwable ex,
                                     final String errorCode,
                                     final HttpServletRequest request, final HttpServletResponse response) {
        if (!log.isInfoEnabled()) {
            return;
        }

    }

}
