package com.hq2808.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.hq2808.blog.auth.TokenAuthService;

import io.jsonwebtoken.JwtException;

public class StatelessAuthenticationFilter extends GenericFilterBean {
	
	private final TokenAuthService tokenAuthenticationService;

	public StatelessAuthenticationFilter(TokenAuthService tokenAuthenticationService) {
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			Authentication authentication = tokenAuthenticationService
					.generateAuthenticationFromRequest((HttpServletRequest) request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
			SecurityContextHolder.clearContext();
		} catch (AuthenticationException | JwtException e) {
			SecurityContextHolder.clearContext();
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
	}
}
