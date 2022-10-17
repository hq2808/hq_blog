package com.hq2808.blog.base;

public class ExpiredException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpiredException(String errorMessage) {
		super(errorMessage);
	}

	public ExpiredException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
