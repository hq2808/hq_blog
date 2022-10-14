package com.hq2808.blog.service;

import javax.management.relation.RoleInfoNotFoundException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hq2808.blog.dto.User;

public interface UserService extends UserDetailsService{
	User findByUsername(String username);
	User saveNewUser(User user) throws RoleInfoNotFoundException;
}
