package com.hq2808.blog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.hq2808.blog.validation.Create;
import com.hq2808.blog.validation.Update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {
	/** The name. */
	@NotBlank(groups = { Create.class }, message = "Please enter user full name.")
	private String name;

	/** The email. */
	@NotBlank(groups = { Create.class }, message = "Please enter user email.")
	@Pattern(
		groups = { Create.class, Update.class },
		regexp = "[a-zA-Z0-9._+-]+(\\.[_a-zA-Z0-9]+)*@[_a-zA-Z0-9._+-]+(\\.[_a-zA-Z0-9]+)+",
		message = "Please enter a valid email address."
	)
	private String email;
	
	@NotBlank(groups = { Create.class }, message = "validation.input.required")
	@Pattern(groups = { Create.class, Update.class }, regexp = "^(?=.{1,20}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z0-9._-]+(?<![_.-])$", message = "Please enter user username")
	private String username;
	
	@NotBlank(groups = { Create.class }, message = "Please enter user password.")
	@Pattern(
		groups = { Create.class, Update.class },
		regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$",
		message = "Please enter a valid password."
	)
	private String password;
	
	/** The full name. */
	private String fullname;

	/** The age. */
	private Integer age;
}
