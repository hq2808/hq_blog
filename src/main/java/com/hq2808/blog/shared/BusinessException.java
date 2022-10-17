package com.hq2808.blog.shared;

public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String errorMessage) {
		super(errorMessage);
	}
	
	public BusinessException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
