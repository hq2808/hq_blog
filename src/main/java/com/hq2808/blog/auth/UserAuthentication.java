package com.hq2808.blog.auth;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.hq2808.blog.dto.auth.UserDetail;

public class UserAuthentication implements Authentication{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The user. */
	private final UserDetail user;

	/** The authenticated. */
	private boolean authenticated = true;

	public UserAuthentication(UserDetail user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return user.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	@Override
	public UserDetail getDetails() {
		return user;
	}

	@Override
	public UserDetail getPrincipal() {
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}

}
