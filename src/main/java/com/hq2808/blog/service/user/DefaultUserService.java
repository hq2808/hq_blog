package com.hq2808.blog.service.user;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.management.relation.RoleInfoNotFoundException;
import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hq2808.blog.dto.User;
import com.hq2808.blog.dto.UserSignUpDto;
import com.hq2808.blog.entity.AuthorityEntity;
import com.hq2808.blog.entity.user.UserEntity;
import com.hq2808.blog.enumerate.Roles;
import com.hq2808.blog.repository.AuthorityRepository;
import com.hq2808.blog.repository.user.UserRepository;
import com.hq2808.blog.shared.BusinessException;

@Service
public class DefaultUserService implements UserService{
	
	private static final String DEFAULT_ROLE = "ROLE_USER";
	
//	@Autowired
//	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	/** The password encoder. */
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//	    return new BCryptPasswordEncoder();
//	}

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
	public User saveNewUser(User user) throws RoleInfoNotFoundException, RoleNotFoundException {
		
//		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Optional<AuthorityEntity> optionalAuth = this.authRepo.findByAuthority(DEFAULT_ROLE);
		
	      if (optionalAuth.isPresent()) {
	    	  AuthorityEntity authority = optionalAuth.get();
	            Collection<AuthorityEntity> authorities = Collections.singletonList(authority);
	            user.setAuthorities(authorities);
	            System.err.println("User after Roles: " + user);  // for testing debugging purposes
//	            return blogUserRepository.save(blogUser);
	            return this.repo.save(UserEntity.toEntity(user)).toDomain();
	        } else {
	            throw new RoleNotFoundException("Default role not found for blog user with username " + user.getUsername());
	        }
	}

	@Override
	public User signup(UserSignUpDto user) throws BusinessException {
		Optional<UserEntity> oUser = this.repo.findByUsernameOrEmail(user.getUsername(), user.getEmail());
		if (oUser.isPresent()) {
			throw new BusinessException("business_exception.user.username_or_email_registed");
		}
		UserEntity newUser = UserEntity.createUserActive(user.getUsername(), user.getEmail(), user.getPassword(), Roles.USER, user.getFullname());
		return this.repo.save(newUser).toDomain();
	}

}
