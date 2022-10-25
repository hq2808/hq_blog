package com.hq2808.blog.entity.user;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;
import org.hibernate.annotations.GenericGenerator;
import com.hq2808.blog.dto.User;
import com.hq2808.blog.entity.AuthorityEntity;
import com.hq2808.blog.entity.BaseEntity;
import com.hq2808.blog.entity.PostEntity;
import com.hq2808.blog.enumerate.Roles;
import com.hq2808.blog.enumerate.UserStatus;

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
	
	/** The age. */
	@Column(name = "age")
	private Integer age;
	
	/** The list post. */
	@BatchFetch(value = BatchFetchType.IN)
	@OneToMany(mappedBy = "userEntity")
	private List<PostEntity> posts;
	
	/** The role. */
	@Column(name = "role")
	private String role;
	
	/** The status. */
	@Column(name = "status")
	private Integer status;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Collection<AuthorityEntity> authorities;
	
	/** The auth id. */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "auth_id")
	private AuthEntity auth;
	
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
				.fullname(this.fullname)
				.age(this.age)
				.auth(this.auth)
				.role(Roles.get(this.role))
				.authorities(this.authorities)
				.status(this.status)
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
				.age(domain.getAge())
				.auth(domain.getAuth())
				.role(domain.getRole() != null ? domain.getRole().getValue() : null)
				.status(domain.getStatus())
				.authorities(domain.getAuthorities())
				.build();
	}
	
	public static UserEntity createUserActive(String username, String email, String password, Roles role, String fullname) {
		return UserEntity.builder()
				.id(UUID.randomUUID().toString())
				.username(username)
				.email(email)
				.password(password)
				.role(role.getValue())
				.fullname(fullname)
				.status(UserStatus.ACTIVE.value)
				.build();
	}
}
