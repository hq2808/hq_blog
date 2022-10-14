package com.hq2808.blog.service.Impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.management.relation.RoleInfoNotFoundException;
import javax.management.relation.RoleNotFoundException;

import org.martynas.blogapp.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hq2808.blog.dto.User;
import com.hq2808.blog.entity.AuthorityEntity;
import com.hq2808.blog.entity.UserEntity;
import com.hq2808.blog.repository.AuthorityRepository;
import com.hq2808.blog.repository.UserRepository;
import com.hq2808.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private static final String DEFAULT_ROLE = "ROLE_USER";
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository repo;
	
	private AuthorityRepository authRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> optUser = this.repo.findByUsername(username);
		if(optUser.isPresent()) {
			return (UserDetails) optUser.get();
		} else {
			throw new UsernameNotFoundException("No user found with username " + username);
		}
	}

	@Override
	public User findByUsername(String username) {
		return this.repo.findByUsername(username).get().toDomain();
	}

	@Override
	public User saveNewUser(User user) throws RoleInfoNotFoundException {
		
		user.setPassword(this.bcryptEncoder.encode(user.getPassword()));
		
		Optional<AuthorityEntity> optionalAuth = this.authRepo.findByAuthority(DEFAULT_ROLE);
		
	      if (optionalAuth.isPresent()) {
	    	  AuthorityEntity authority = optionalAuth.get();
	            Collection<AuthorityEntity> authorities = Collections.singletonList(authority);
	            user.setAuthorities(authorities);
	            System.err.println("blogUser after Roles: " + blogUser);  // for testing debugging purposes
//	            return blogUserRepository.save(blogUser);
	            return this.blogUserRepository.saveAndFlush(blogUser);
	        } else {
	            throw new RoleNotFoundException("Default role not found for blog user with username " + blogUser.getUsername());
	        }
	}

}
