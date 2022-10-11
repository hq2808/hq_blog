package com.hq2808.blog.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;
import org.eclipse.persistence.annotations.UuidGenerator;

import com.hq2808.blog.dto.Posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The Class AgencyEntity.
 */
@Entity
@Table(name = "HQ_POSTS")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostsEntity extends BaseEntity{
	
	/** The id. */
	@Id
	@GeneratedValue(generator = "uuid")
	@UuidGenerator(name = "uuid")
	@Basic(optional = false)
	@Column(name = "ID")
	private String id;
	
	/** The slug. */
	@Column(name = "SLUG")
	private String slug;
	
	/** The title. */
	@Column(name = "TITLE")
	private String title;
	
	/** The content. */
	@Column(name = "CONTENT")
	private String content;
	
	/** The image. */
	@Column(name = "IMAGE")
	private String image;
	
	/** The status. */
	@Column(name = "STATUS")
	private int status;
	
	/** The published. */
	@Column(name = "PUBLISHED")
	private Boolean published;
	
	/** The user. */
	@JoinFetch(value = JoinFetchType.INNER)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserEntity userEntity;
	
	public Posts toDomain() {
		return Posts.builder()
				.id(this.id)
				.slug(this.slug)
				.title(this.title)
				.content(this.content)
				.image(this.image)
				.status(this.status)
				.published(this.published)
				.user(this.userEntity.toDomain())
				.build();
	}
	
	public static PostsEntity toEntity(Posts domain) {
		if(domain == null) {
			return null;
		}
		return PostsEntity.builder()
				.id(domain.getId())
				.slug(domain.getSlug())
				.title(domain.getTitle())
				.content(domain.getContent())
				.image(domain.getImage())
				.status(domain.getStatus())
				.published(domain.getPublished())
				.userEntity(UserEntity.toEntity(domain.getUser()))
				.build();
	}
	
}
