package com.hq2808.blog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hq2808.blog.auth.AppUserDetailsService;
import com.hq2808.blog.auth.TokenAuthService;
import com.hq2808.blog.filter.StatelessAuthenticationFilter;
import com.hq2808.blog.filter.StatelessLoginFilter;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private TokenAuthService tokenAuthenticationService;

	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/admin/css/**", "/admin/img/**", "/admin/js/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
//		if (Arrays.stream(this.env.getActiveProfiles()).anyMatch(envi -> envi.equals("dev") || envi.equals("devlocal"))) {
//			configuration.setAllowedOrigins(Arrays.asList("*"));
//		} else {
//			String homepage = this.env.getProperty("blog.homepage");
//			if (homepage.substring(homepage.length() - 1).equalsIgnoreCase("/")) {
//				homepage = homepage.substring(0, homepage.length() - 1);
//			}
//			configuration.setAllowedOrigins(Arrays.asList(homepage));
//		}
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", TokenAuthService.AUTH_HEADER_NAME, "x-file-name"));
		configuration.setExposedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", TokenAuthService.AUTH_HEADER_NAME, "x-file-name"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http	
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/webapi/post/*, /webapi/post").permitAll()
		.antMatchers(HttpMethod.GET, "/webapi/home/*").permitAll()
		.antMatchers(HttpMethod.POST, "/webapi/user/signup").permitAll();
//		.antMatchers("/webapi", "/webapi/**").authenticated();
		
		http.addFilterBefore(
				new StatelessLoginFilter("/auth/login",
						tokenAuthenticationService,
						appUserDetailsService,
						authenticationManager()),
				UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(
				new StatelessAuthenticationFilter(tokenAuthenticationService),
				UsernamePasswordAuthenticationFilter.class);
	}
}
