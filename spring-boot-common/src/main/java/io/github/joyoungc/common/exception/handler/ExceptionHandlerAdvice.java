package io.github.joyoungc.common.exception.handler;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.ContentCachingRequestWrapper;

import io.github.joyoungc.common.Codes.Error;
import io.github.joyoungc.common.ErrorResponse;
import io.github.joyoungc.common.exception.BaseException;
import io.github.joyoungc.common.exception.ExceptionProperties;
import io.github.joyoungc.common.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice implements InitializingBean {

	/*
	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse internalServerError(HttpServletRequest request, Exception ex) {
		log.error("##### internalServerError #####");
		log.error("Request: {}, raised: {}", request.getRequestURL(), ex.getMessage());
		
		ErrorResponse errorResponse = new ErrorResponse(Error.INTERNAL_SERVER_ERROR);
		errorResponse.setCause(ex.getMessage());
		return errorResponse;
	}
	*/
	
	@ExceptionHandler(value = BaseException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse baseExceptionError(HttpServletRequest request, BaseException ex) {
		log.error("##### baseExceptionError #####");
		log.error("Request: {}, raised: {}", request.getRequestURL(), ex.getMessage());
		
		if (request instanceof ContentCachingRequestWrapper) {
			ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
			String requestBody = new String(cachingRequest.getContentAsByteArray(), Charset.defaultCharset());
			log.error("Request Body : {}", requestBody);
		}
		return new ErrorResponse(Error.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NoDataFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse noDataFoundError(HttpServletRequest req, Exception ex) {
		log.error("##### noDataFoundError #####");
		log.error("Request: {}, raised: {}", req.getRequestURL(), ex.getMessage());
		return new ErrorResponse(Error.NO_DATA_FOUND);
	}

	@ExceptionHandler(value = { MissingServletRequestParameterException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse badRequestError(HttpServletRequest req, Exception ex) {
		log.error("##### badRequestError #####");
		log.error("Request: {}, raised: {}", req.getRequestURL(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(Error.BAD_REQUEST);
		errorResponse.setCause(ex.getMessage());
		return errorResponse;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse notValidRequestParameterError(HttpServletRequest req, MethodArgumentNotValidException ex) {
		log.error("##### notValidRequestParameterError #####");
		log.error("Request: {}, raised: {}", req.getRequestURL(), ex.getMessage());
		FieldError fieldError = ex.getBindingResult().getFieldError();
		ErrorResponse errorResponse = new ErrorResponse(Error.BAD_REQUEST);
		errorResponse.setCause("[" + fieldError.getField() + "] " + fieldError.getDefaultMessage());
		return errorResponse;
	}

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public ErrorResponse methodNotSupportedError(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
		log.error("##### methodNotSupportedError #####");
		log.error("Request: {}, raised: {}", req.getRequestURL(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(Error.METHOD_NOT_ALLOWED);
		errorResponse.setCause(ex.getMessage());
		return errorResponse;
	}
	
	@Autowired
    private ExceptionProperties exceptionProperties;
 
    @Autowired
    private ApplicationContext applicationContext;

    @SuppressWarnings("rawtypes")
    private List<ExceptionResponse> exceptionResponse = new ArrayList<>();
 
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> doResolveException(@RequestBody(required = false) final Object requestBodyAsString,
            final HttpServletRequest request, final HttpServletResponse response, final Object handler,
            final Exception ex) {
        return this.createResponseEntity(request, response, ex);
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private ResponseEntity<Object> createResponseEntity(final HttpServletRequest request,
            final HttpServletResponse response, final Exception ex) {
        for (ExceptionResponse m : exceptionResponse) {
            if (m.isSupport(ex, request, response)) {
                log.debug("{} is handled by {}", ex.getClass(), m.getClass().getName());
                return m.handle(ex, request, response);
            }
        }
        return new ResponseEntity<>(ex.getMessage(), null, exceptionProperties.getDefaultHttpStatusCode());
    }
 
    /*
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final Object body,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
 
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
 
        HttpServletRequest request2 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        ResponseEntity<Object> responseEntity = this.createResponseEntity(request2, null, ex);
        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getHeaders(), status);
    }
    */
 
    @SuppressWarnings("rawtypes")
    @Override
    public void afterPropertiesSet() throws Exception {
 
        if (exceptionResponse != null) {
            // Find all ExceptionResponse in the ApplicationContext, including
            // ancestor contexts.
            Map<String, ExceptionResponse> matchingBeans = BeanFactoryUtils
                    .beansOfTypeIncludingAncestors(applicationContext, ExceptionResponse.class, true, false);
            if (!matchingBeans.isEmpty()) {
                this.exceptionResponse = new ArrayList<>(matchingBeans.values());
                AnnotationAwareOrderComparator.sort(this.exceptionResponse);
            }
        }
        if (exceptionResponse != null) {
            for (ExceptionResponse er : exceptionResponse) {
                log.info("add ExceptionResponse:{}", er.getClass());
            }
        }
    }

}
