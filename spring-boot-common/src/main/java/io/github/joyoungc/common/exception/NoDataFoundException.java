package io.github.joyoungc.common.exception;

public class NoDataFoundException extends RuntimeException {

	private static final long serialVersionUID = -2524151065036815942L;
	
	private String errorCode = "";
	
	public NoDataFoundException() {}
	
	public NoDataFoundException(String message) {
		super(message);
	}
	
	public NoDataFoundException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
