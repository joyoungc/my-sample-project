package io.github.joyoungc.common.exception;

import java.util.Arrays;

/**
 * BaseException
 * 
 * @author Joyoungc
 * @date 2018.09.18
 */
public class BaseException extends RuntimeException {

	protected static final long serialVersionUID = 1L;

	private String errorCode;

	private String[] messageArgs;

	public BaseException(String errorCode) {
		this(errorCode, null, (String[]) null);
	}

	public BaseException(String errorCode, Throwable cause) {
		this(errorCode, cause, (String[]) null);
	}

	public BaseException(String errorCode, String... messageArgs) {
		this(errorCode, null, messageArgs);
	}

	public BaseException(String errorCode, Throwable cause, String... messageArgs) {
		super(cause != null ? cause.getMessage() : errorCode, cause);

		if (errorCode == null)
			throw new IllegalArgumentException("Exception Code can not be null.");

		this.errorCode = errorCode;

		if (messageArgs != null) {
			this.messageArgs = Arrays.copyOf(messageArgs, messageArgs.length, String[].class);
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String[] getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(String[] messageArgs) {
		this.messageArgs = messageArgs;
	}

}
