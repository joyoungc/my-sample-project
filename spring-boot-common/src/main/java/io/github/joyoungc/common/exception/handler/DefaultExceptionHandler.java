package io.github.joyoungc.common.exception.handler;

import io.github.joyoungc.common.exception.ExceptionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler implements InitializingBean {

    @Autowired
    @Qualifier("errorMessageSource")
    MessageSource messageSource;

    /*
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> baseExceptionHandler(@RequestBody(required = false) final Object requestBodyAsString,
                                                     final HttpServletRequest request,
													 final HttpServletResponse response, final Object handler,
                                                     final BaseException ex) {

        ErrorResponse errorResponse = new ErrorResponse(Error.COM0001);
        errorResponse.setMessage(messageSource.getMessage(Error.COM0001.getCode(),null,null));

        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse,null, HttpStatus
        .INTERNAL_SERVER_ERROR);

        return responseEntity;

        //return this.createResponseEntity(request, response, ex);
    }
    */

    /*
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationExceptionHandler(HttpServletRequest request,
                                                                     final ApplicationException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ex.getErrorCode());
        errorResponse.setStatus(ex.getStatus().value());
        errorResponse.setMessage(messageSource.getMessage(ex.getErrorCode(), null, null));

        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, null,
                ex.getStatus());

        return responseEntity;

        //return this.createResponseEntity(request, response, ex);
    }


    /*
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
        return new ErrorResponse(Codes.ErrorOld.NO_DATA_FOUND);
    }



    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorResponse methodNotSupportedError(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
        log.error("##### methodNotSupportedError #####");
        log.error("Request: {}, raised: {}", req.getRequestURL(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(Codes.ErrorOld.METHOD_NOT_ALLOWED);
        errorResponse.setCause(ex.getMessage());
        return errorResponse;
    }
    */

    @Autowired
    private ExceptionProperties exceptionProperties;

    @Autowired
    private ApplicationContext applicationContext;

    private List<ExceptionResponse> exceptionResponse = new ArrayList<>();

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> doResolveException(@RequestBody(required = false) final Object requestBodyAsString,
                                                     final HttpServletRequest request,
                                                     final HttpServletResponse response, final Object handler,
                                                     final Exception ex) {
        return this.createResponseEntity(request, response, ex);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
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
