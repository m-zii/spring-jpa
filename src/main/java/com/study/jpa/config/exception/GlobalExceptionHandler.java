package com.study.jpa.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
		return ResponseEntity
				.status(ErrorCode.RUNTIME_EXCEPTION.getStatus())
				.body(ErrorResponse.builder()
						.errorCode(ErrorCode.RUNTIME_EXCEPTION.getCode())
						.errorMessage(e.getMessage())
						.build());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
		return ResponseEntity
				.status(ErrorCode.ACCESS_DENIED_EXCEPTION.getStatus())
				.body(ErrorResponse.builder()
						.errorCode(ErrorCode.ACCESS_DENIED_EXCEPTION.getCode())
						.errorMessage(e.getMessage())
						.build());
	}
    
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final IOException e) {
		return ResponseEntity
				.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
				.body(ErrorResponse.builder()
						.errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
						.errorMessage(e.getMessage())
						.build());
	}
    
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final EntityNotFoundException e) {
		return ResponseEntity
				.status(ErrorCode.NOT_FOUND_EXTENSION.getStatus())
				.body(ErrorResponse.builder()
						.errorCode(ErrorCode.NOT_FOUND_EXTENSION.getCode())
						.errorMessage(e.getMessage())
						.build());
	}
    
    @ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final ApiException e) {
    	return ResponseEntity
        		.status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                		.errorCode(e.getErrorCode().getCode())
                        .errorMessage(e.getErrorCode().getMessage())
                        .build());
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final Exception e) {
		return ResponseEntity
				.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
				.body(ErrorResponse.builder()
						.errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
						.errorMessage(e.getMessage())
						.build());
	}
}
