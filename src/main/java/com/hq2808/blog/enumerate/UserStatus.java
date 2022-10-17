package com.hq2808.blog.enumerate;

public enum UserStatus {
	
	/** The inactivce. */
	INACTIVE(0, "INACTIVE"),
	
	/** The active. */
	ACTIVE(1, "ACTIVE");

	/** The value. */
	public int value;

	/** The description. */
	public String description;
	
	private UserStatus(int value, String description) {
		this.value = value;
		this.description = description;
	}
}
