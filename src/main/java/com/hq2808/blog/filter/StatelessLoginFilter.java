package com.hq2808.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hq2808.blog.auth.AppUserDetailsService;
import com.hq2808.blog.auth.TokenAuthService;
import com.hq2808.blog.auth.UserAuthentication;
import com.hq2808.blog.dto.auth.AuthUserDto;
import com.hq2808.blog.dto.auth.UserDetail;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	/** The token authentication service. */
	private final TokenAuthService tokenAuthenticationService;

	/** The user service. */
	private final AppUserDetailsService userService;
	
	/**
	 * Instantiates a new stateless login filter.
	 *
	 * @param urlMapping
	 *            the url mapping
	 * @param tokenAuthenticationService
	 *            the token authentication service
	 * @param userService
	 *            the user service
	 * @param authenticationManager
	 *            the authentication manager
	 */
	public StatelessLoginFilter(String urlMapping, TokenAuthService tokenAuthenticationService,
			AppUserDetailsService userService, AuthenticationManager authenticationManager) {
		super(urlMapping);
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.userService = userService;
		setAuthenticationManager(authenticationManager);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (!request.getMethod().equals("POST")) {
			return null;
		}
		final AuthUserDto user = this.toUser(request);
		final UsernamePasswordAuthenticationToken loginToken = user.toAuthenticationToken();
		return getAuthenticationManager().authenticate(loginToken);
	}
	
	/**
	 * To user.
	 *
	 * @param request
	 *            the request
	 * @return the admin user detail
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private AuthUserDto toUser(HttpServletRequest request) throws IOException {
		return new ObjectMapper().readValue(request.getInputStream(), AuthUserDto.class);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		final UserDetail authenticatedUser = this.userService.loadUserByUsername(authResult.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
		this.tokenAuthenticationService.addJwtTokenToHeader(response, userAuthentication);
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain,
	 * org.springframework.security.core.Authentication)
	 */
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		final UserDetail authenticatedUser = this.userService.loadUserByUsername(authResult.getName());
//		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
//		this.tokenAuthenticationService.addJwtTokenToHeader(response, userAuthentication);
//		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
//	}
	
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		final UserDetail authenticatedUser = this.userService.loadUserByUsername(authResult.getName());
//		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
//		try {
//			this.tokenAuthenticationService.addJwtTokenToHeader(response, userAuthentication);
////			this.userService.loginSuccessHandle(authResult.getName());
//		} catch (Exception e) {
//			SecurityContextHolder.clearContext();
//			logger.info("Authentication Fail");
//			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		}
//		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
//	}
//	
//	/**
//	 * Unsuccessful authentication.
//	 *
//	 * @param request the request
//	 * @param response the response
//	 * @param failed the failed
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 * @throws ServletException the servlet exception
//	 */
//	@Override
//	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException failed) throws IOException, ServletException {
//		response.setContentType("application/json");
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		PrintWriter out = response.getWriter();
//		// Handle bad credential and locked result
//		if (failed instanceof BadCredentialsException) {
//			System.out.println("Faild");
//		} else if (failed instanceof LockedException) {
//			System.out.println("Faild");
//		}
//		out.flush();
//	}
	
}
