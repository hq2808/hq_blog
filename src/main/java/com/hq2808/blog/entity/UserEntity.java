package com.hq2808.blog.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;
import org.hibernate.annotations.GenericGenerator;

import com.hq2808.blog.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "HQ_USER")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	/** The email. */
	@Basic(optional = false)
	@Column(name = "EMAIL")
	private String email;
	
	/** The password. */
	@Basic(optional = false)
	@Column(name = "PASSWORD")
	private String password;
	
	/** The fullname. */
	@Column(name = "FULLNAME")
	private String fullname;
	
	/** The username. */
	@Basic(optional = false)
	@Column(name = "USERNAME")
	private String username;
	
	/** The list post. */
	@BatchFetch(value = BatchFetchType.IN)
	@OneToMany(mappedBy = "userEntity")
	private List<PostsEntity> posts;
	
	/**
	 * To domain.
	 *
	 * @return the user
	 */
	public User toDomain() {
		return User.builder()
				.id(this.id)
				.username(this.username)
				.email(this.email)
				.password(this.password)
				.fullname(this.fullname)
				.build();
	}
	
	/**
	 * To entity.
	 *
	 * @param domain the domain
	 * @return the user entity
	 */
	public static UserEntity toEntity(User domain) {
		if (domain == null) { 
			return null;
		}
		return UserEntity.builder()
				.id(domain.getId())
				.username(domain.getUsername())
				.email(domain.getEmail())
				.password(domain.getPassword())
				.fullname(domain.getFullname())
				.build();
	}
}
