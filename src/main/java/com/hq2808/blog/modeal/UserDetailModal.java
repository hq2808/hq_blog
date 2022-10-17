package com.hq2808.blog.modeal;

import com.hq2808.blog.entity.user.AuthEntity;
import com.hq2808.blog.enumerate.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class UserDetailModal.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailModal {

	/** The user id. */
	private String userId;

	/** The auth id. */
	private AuthEntity auth;
	
	/** The auth id. */
	private String authId;

	/** The full name. */
	private String fullname;

	/** The email. */
	private String email;

	/** The status. */
	private UserStatus status;

	/** The age. */
	private Integer age;
	
	private Integer role;
	
	private String password;
	
	private Integer statusActive;
}
