package com.hq2808.blog.service.user;

import java.util.List;

import javax.management.relation.RoleInfoNotFoundException;
import javax.management.relation.RoleNotFoundException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hq2808.blog.dto.User;
import com.hq2808.blog.dto.UserSignUpDto;
import com.hq2808.blog.shared.BusinessException;

public interface UserService extends UserDetailsService{
	
	/**
	 * Signup.
	 *
	 * @param user the user
	 */
	User signup(UserSignUpDto user) throws BusinessException;
	
	List<User> getAll();
	
	User findByUsername(String username);
	User saveNewUser(User user) throws RoleInfoNotFoundException, RoleNotFoundException;
}
