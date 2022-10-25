package com.hq2808.blog.dto;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.hq2808.blog.entity.AuthorityEntity;
import com.hq2808.blog.entity.user.AuthEntity;
import com.hq2808.blog.enumerate.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	/** The id. */
	private String id;
	
	/** The username. */
	@NotBlank(message = "validation.input.required")
	private String username;

	/** The email. */
	@NotBlank(message = "validation.input.required")
	@Pattern(regexp = "[a-zA-Z0-9._+-]+(\\.[_a-zA-Z0-9]+)*@[_a-zA-Z0-9._+-]+(\\.[_a-zA-Z0-9]+)+", message = "validation.input.email")
	private String email;

	/** The password. */
	@NotBlank(message = "validation.input.required")
	private String password;

	/** The full name. */
	private String fullname;
	
	/** The age. */
	private Integer age;
	
	/** The list posts. */
	private List<Post> post;
	
	/** The roles. */
	private Roles role;
	
	/** The status. */
	private Integer status;
	
	private Collection<AuthorityEntity> authorities;
	
	private AuthEntity auth;
}
