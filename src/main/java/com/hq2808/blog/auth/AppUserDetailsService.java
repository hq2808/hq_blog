package com.hq2808.blog.auth;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hq2808.blog.dto.auth.UserDetail;
import com.hq2808.blog.entity.user.UserEntity;
import com.hq2808.blog.repository.user.UserRepository;

@Component
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> optUser = this.userRepo.findByUsernameOrEmail(username, username);
		if(!optUser.isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}
		UserDetail userDetail = new UserDetail(optUser.get().getUsername()
				, optUser.get().getPassword()
				, Arrays.asList(new SimpleGrantedAuthority(optUser.get().getRole())));
		return userDetail;
	}

}
