package com.hq2808.blog.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hq2808.blog.controller.BaseController;
import com.hq2808.blog.dto.UserSignUpDto;
import com.hq2808.blog.response.Response;
import com.hq2808.blog.service.user.UserService;
import com.hq2808.blog.shared.BusinessException;
import com.hq2808.blog.validation.Create;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public Response signUp(@Validated({ Create.class })  @RequestBody UserSignUpDto userSignUp) throws BusinessException {
		userSignUp.setPassword(this.passwordEncoder.encode(userSignUp.getPassword()));
		return Response.build().data(this.userService.signup(userSignUp));
	}
}
