package io.github.joyoungc.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.github.joyoungc.common.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;

import static io.github.joyoungc.common.Codes.Error;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse internalServerError(HttpServletRequest req, Exception ex) {
		log.error("##### internalServerError #####");
		log.error("Request: {}, raised: {}", req.getRequestURL(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(Error.INTERNAL_SERVER_ERROR);
		errorResponse.setCause(ex.getMessage());
		return errorResponse;
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

	@ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public ErrorResponse methodNotSupportedError(HttpServletRequest req,
			HttpRequestMethodNotSupportedException ex) {
		log.error("##### methodNotSupportedError #####");
		log.error("Request: {}, raised: {}", req.getRequestURL(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(Error.METHOD_NOT_ALLOWED);
		errorResponse.setCause(ex.getMessage());
		return errorResponse;
	}

}
