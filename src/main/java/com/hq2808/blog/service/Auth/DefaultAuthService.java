package com.hq2808.blog.service.Auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq2808.blog.entity.user.AuthEntity;
import com.hq2808.blog.repository.user.AuthRepository;

@Service
public class DefaultAuthService implements AuthService{
	
	@Autowired
	private AuthRepository repo;

	@Override
	public Optional<AuthEntity> findByUsername(String username) {
		return this.repo.findByUsername(username);
	}

}
