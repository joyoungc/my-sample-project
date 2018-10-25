package io.github.joyoungc.common;

import org.springframework.http.HttpStatus;

/***
 * Created by Aiden Jeong on 2018.10.22
 */
public enum CommonError implements ErrorCode {

    COMMON_INTERNAL_SERVER_ERROR("COM1N0001", HttpStatus.INTERNAL_SERVER_ERROR),
    COMMON_BAD_REQUEST("COM1N0002", HttpStatus.BAD_REQUEST),
    COMMON_UNAUTHORIZED("COM1N0003", HttpStatus.UNAUTHORIZED),
    COMMON_NOT_FOUND("COM1N0004", HttpStatus.NOT_FOUND),
    COMMON_ETC("COM1N9999", HttpStatus.INTERNAL_SERVER_ERROR);

    private HttpStatus httpStatus;
    private String code;

    CommonError(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return this.getStatus();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
