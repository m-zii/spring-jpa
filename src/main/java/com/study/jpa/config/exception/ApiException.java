package com.study.jpa.config.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public ApiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
