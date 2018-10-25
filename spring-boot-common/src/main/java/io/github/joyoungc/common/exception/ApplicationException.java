package io.github.joyoungc.common.exception;

import io.github.joyoungc.common.ErrorCode;
import org.springframework.http.HttpStatus;

/***
 * Created by Aiden Jeong on 2018.10.22
 */
public class ApplicationException extends BaseException {

    private HttpStatus httpStatus;

    public ApplicationException(ErrorCode error) {
        super(error.getCode());
        this.httpStatus = HttpStatus.valueOf(error.getStatus());
    }

    public ApplicationException(ErrorCode error, String... messageArgs) {
        super(error.getCode(), messageArgs);
        this.httpStatus = HttpStatus.valueOf(error.getStatus());
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
