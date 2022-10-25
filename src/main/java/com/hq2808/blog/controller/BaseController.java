package com.hq2808.blog.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hq2808.blog.base.ExpiredException;
import com.hq2808.blog.dto.User;
import com.hq2808.blog.entity.user.UserEntity;
import com.hq2808.blog.repository.user.UserRepository;

public abstract class BaseController {
	
	@Autowired
	private UserRepository userRepository;
	
	protected Optional<User> getCurrentUser() throws ExpiredException {
		UserEntity userEntity = this.getCurrentUserEntity()
				.orElseThrow(() -> new ExpiredException("Token expired"));
		return Optional.of(userEntity.toDomain());
	}
	
	protected Optional<UserEntity> getCurrentUserEntity() {
		Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
		if (userAuthentication == null) {
			return Optional.empty();
		}
		String principal = userAuthentication.getName();
		return this.userRepository.findByUsernameOrEmail(principal, principal);
	}
}
