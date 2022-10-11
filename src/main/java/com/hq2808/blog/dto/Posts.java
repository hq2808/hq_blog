package com.hq2808.blog.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
	/** The id. */
	private String id;
	
	/** The slug. */
	private String slug;
	
	/** The title. */
	@NotBlank(message = "validation.input.required")
	private String title;
	
	/** The content. */
	@NotBlank(message = "validation.input.required")
	private String content;
	
	/** The image. */
	private String image;
	
	/** The status. */
	private int status;
	
	/** The published. */
	private Boolean published;
	
	/** The user. */
	private User user;
}
