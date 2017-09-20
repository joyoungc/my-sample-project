package io.github.joyoungc.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.github.joyoungc.common.Error;;

@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
	private int code;
	private String message;	
	private String cause;
	
	public ErrorResponse() {}
	
	public ErrorResponse(Error error) {
		this.code = error.getCode();
		this.message = error.getDescription();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
