package com.hq2808.blog.shared;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBindException(BindException e) {
	    String errorMessage = "Request không hợp lệ";
	    if (e.getBindingResult().hasErrors()) {
	    	errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	    }
	    return errorMessage;
	}
}
