package com.hq2808.blog.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hq2808.blog.dto.auth.UserDetail;

@Service
public class TokenAuthService {
	public static final String AUTH_HEADER_NAME = "x-auth-token";
	
	@Autowired
	private JwtTokenHandler jwtTokenHandler;
	
	public void addJwtTokenToHeader(HttpServletResponse response, UserAuthentication authentication) {
		final UserDetail user = authentication.getDetails();
		response.addHeader(AUTH_HEADER_NAME, jwtTokenHandler.createTokenForUser(user));
	}
	
	public Authentication generateAuthenticationFromRequest(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if(token == null || token.isEmpty()) {
			return null;
		}
		return this.jwtTokenHandler.parseUserFromToken(token)
				.map(UserAuthentication::new)
				.orElse(null);
	}
}
