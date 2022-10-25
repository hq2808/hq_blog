package com.hq2808.blog.auth;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.hq2808.blog.dto.auth.UserDetail;

@Component
public class JwtTokenHandler {
	
	@Value("${blog.jwt.secret}")
	private String secret;
	
	@Autowired
    private AppUserDetailsService applicationUserService;
	
	Optional<UserDetail> parseUserFromToken(String token) {
    	Claims claims = Jwts.parser()
		        .setSigningKey(secret)
		        .parseClaimsJws(token)
		        .getBody();
        String username = (String) claims.get("username");
        return Optional.ofNullable(applicationUserService.loadUserByUsername(username));
    }
	
	public String createTokenForUser(UserDetail user) {
		final ZonedDateTime afterOneWeek = ZonedDateTime.now().plusWeeks(1);
		 List<? extends GrantedAuthority> roles = new ArrayList<>(user.getAuthorities());
		return Jwts.builder()
				.claim("username", user.getUsername())
				.claim("role", roles.isEmpty() ? null : roles.get(0).getAuthority())
				.signWith(SignatureAlgorithm.HS512, secret)
				.setExpiration(Date.from(afterOneWeek.toInstant()))
				.compact();
	}
}
