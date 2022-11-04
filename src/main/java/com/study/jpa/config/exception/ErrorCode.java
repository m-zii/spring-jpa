package com.study.jpa.config.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {
	
	RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "E0004"),

    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "S0001", "게시글이 존재하지 않습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "S0002", "사용자가 존재하지 않습니다."),
    NOT_FOUND_FILE(HttpStatus.NOT_FOUND, "S0003", "파일이 존재하지 않습니다."),
    NOT_FOUND_EXTENSION(HttpStatus.NOT_FOUND, "S0004", "파일 확장자가 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private String message;
    
    ErrorCode (HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }
    
    ErrorCode (HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
