package com.hq2808.blog.enumerate;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum Roles {
	ADMIN("ROLE_ADMIN", "ADMIN", "roles.admin"),
	USER("ROLE_USER", "USER", "roles.user");
	
	/** The value. */
	private String value;

	/** The description. */
	private String description;
	
	private String label;
	
	/**
	 * Instantiates a new roles.
	 *
	 * @param value the value
	 * @param description the description
	 * @param label the label
	 * @param type the type
	 */
	private Roles(String value, String description, String label) {
		this.value = value;
		this.description = description;
		this.label = label;
	}
	
	private static final Map<String, Roles> lookup = new HashMap<>();
	static {
		for (Roles t : Roles.values()) {
			lookup.put(t.getValue(), t);
		}
	}
	
	public static Roles get(String value) { 
		return lookup.get(value); 
	}
}
