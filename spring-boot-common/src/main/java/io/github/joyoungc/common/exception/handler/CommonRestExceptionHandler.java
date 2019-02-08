package io.github.joyoungc.common.exception.handler;

import io.github.joyoungc.common.CommonError;
import io.github.joyoungc.common.ErrorResponse;
import io.github.joyoungc.common.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by Aiden Jeong on 2018.10.22
 */
@ControllerAdvice
public class CommonRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    @Qualifier("errorMessageSource")
    MessageSource messageSource;

    /**
     * RequestParam 에 값이 누락되었을시 발생
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(CommonError.COMMON_BAD_REQUEST.getCode(), ex.getMessage(),
                status);

        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    /**
     * Validation Annotation에 의한 유효성 검증시 예외사항 발생되었을때
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(CommonError.COMMON_BAD_REQUEST.getCode(), status);

        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        errorResponse.setErrors(errors);

        return handleExceptionInternal(ex, errorResponse, null, status, request);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationExceptionHandler(final ApplicationException ex,
                                                                     WebRequest request) {
        ErrorResponse error = new ErrorResponse(ex.getErrorCode(), messageSource.getMessage(ex.getErrorCode()
                , null, null), ex.getHttpStatus());
        return new ResponseEntity(error, null, ex.getHttpStatus());
    }

}
