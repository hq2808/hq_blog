package com.hq2808.blog.service.Auth;

import java.util.Optional;

import com.hq2808.blog.entity.user.AuthEntity;

public interface AuthService {
	Optional<AuthEntity> findByUsername(String username);
}
