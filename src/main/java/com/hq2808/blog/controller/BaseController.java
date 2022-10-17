package com.hq2808.blog.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hq2808.blog.base.ExpiredException;
import com.hq2808.blog.entity.user.AuthEntity;
import com.hq2808.blog.enumerate.AuthStatus;
import com.hq2808.blog.modeal.UserDetailModal;
import com.hq2808.blog.repository.user.AuthRepository;
import com.hq2808.blog.repository.user.UserRepository;

public abstract class BaseController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	
	protected Optional<UserDetailModal> getCurrentUser() throws ExpiredException {
		AuthEntity currentAuth = this.getCurrentUserEntity(AuthStatus.ACTIVE)
				.orElseThrow(() -> new ExpiredException("token expire"));
		
		return this.userRepository.findByAuthId(currentAuth.getId()).map((model) -> {
			return UserDetailModal.builder().userId(model.getId()).auth(model.getAuth()).age(model.getAge())
					.email(model.getEmail()).fullname(model.getFullname()).build();
		});
	}
	
	protected Optional<AuthEntity> getCurrentUserEntity(AuthStatus authStatus) {
		Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
		if (userAuthentication == null) {
			return Optional.empty();
		}
		String principal = userAuthentication.getName();
		return this.authRepository.findByUsernameAndStatus(principal, authStatus);
	}
}
