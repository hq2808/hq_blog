package com.hq2808.blog.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hq2808.blog.base.response.Response;
import com.hq2808.blog.controller.BaseController;
import com.hq2808.blog.dto.UserSignUpDto;
import com.hq2808.blog.service.user.UserService;
import com.hq2808.blog.shared.BusinessException;

@RestController
@RequestMapping("/auth")
public class UserController extends BaseController{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public Response getAll() {
		return Response.build().data(this.userService.getAll());
	}
	
	@PostMapping("/sign-up")
	public Response signUp(@RequestBody UserSignUpDto userSignUp) throws BusinessException {
		userSignUp.setPassword(this.passwordEncoder.encode(userSignUp.getPassword()));
		return Response.build().data(this.userService.signup(userSignUp));
	}
}
