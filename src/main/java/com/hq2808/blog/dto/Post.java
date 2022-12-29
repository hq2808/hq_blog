package com.hq2808.blog.dto;

import java.time.LocalDateTime;

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
public class Post {
	
	/** The id. */
	private String id;
	
	/** The slug. */
	private String slug;
	
	/** The title. */
	@NotBlank(message = "Tiêu đề không được để trống")
	private String title;
	
	/** The content. */
	@NotBlank(message = "Nội dung không được để trống")
	private String content;
	
	/** The image. */
	private String image;
	
	/** The status. */
	private int status;
	
	/** The published. */
	private Boolean published;
	
	/** The user. */
	private User user;
	
	/** The create at. */
	private LocalDateTime createdAt;
}
