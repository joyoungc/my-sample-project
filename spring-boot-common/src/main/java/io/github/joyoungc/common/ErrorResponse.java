package io.github.joyoungc.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * {“status”:”401”,”message”:”Authenticate”,”code”:200003,”more info”:”http://www.twillo.com/docs/errors/20003"}
 */
@Getter
@Setter
public class ErrorResponse {

    public ErrorResponse() {
    }

    public ErrorResponse(String code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }

    public ErrorResponse(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    private String code;
    private String message;
    private HttpStatus status;
    private String traceId;
    private List<String> errors;

    public int getStatus() {
        return status.value();
    }

}
