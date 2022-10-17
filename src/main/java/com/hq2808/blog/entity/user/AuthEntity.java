package com.hq2808.blog.entity.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hq2808.blog.entity.BaseEntity;
import com.hq2808.blog.enumerate.AuthStatus;
import com.hq2808.blog.enumerate.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "HQ_Auth")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name="last_login_time")
	private Timestamp lastLoginTime;

	@Column(name="password")
	private String password;

	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Roles role;

	@Column(name="status")
	@Enumerated(EnumType.ORDINAL)
	private AuthStatus status;

	@Column(name="username")
	private String username;

	@Column(name="is_first_login")
	private boolean isFirstLogin;
}
