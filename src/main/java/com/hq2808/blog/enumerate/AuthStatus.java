package com.hq2808.blog.enumerate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthStatus {
	ACTIVE(0, "Active"),
	INACTIVE(1, "Inactive"),
	LOCKED(2, "Locked");
	
	public int value;
	public String description;
}