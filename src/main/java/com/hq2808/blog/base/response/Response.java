package com.hq2808.blog.base.response;

import lombok.Getter;

@Getter
public class Response {
	
	public static final int CODE_SUCCESS = 0;

	public static final int CODE_PERMISSION = 1;
	
	public static final int CODE_NOT_FOUND = 2;

	public static final int CODE_ALREADY_EXIST = 3;
	
	public static final int CODE_BUSINESS = 4;
	
	public static final int CODE_LIKE_OLD_PASSWORD = 5;
	
	public static final int CODE_EXPIRED = 8888;

	public static final int CODE_INTERNAL_ERROR = 9999;
	
	public static final int CODE_BAD_REQUEST = 400;
	
	protected Integer code;
	
	private Object data;
	
	public Response ok() {
		this.code = CODE_SUCCESS;
		return this;
	}
	
	public Response code(int code) {
		this.code = code;
		return this;
	}
	
	public Response data(Object data) {
		this.data = data;
		return this;
	}
	
	public static Response build() {
		return new Response();
	}
}
