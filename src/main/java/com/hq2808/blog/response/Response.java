package com.hq2808.blog.response;

import lombok.Getter;

@Getter
public class Response {
	protected Integer code;
	private Object data;
	
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
