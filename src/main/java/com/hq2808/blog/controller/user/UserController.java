package com.hq2808.blog.controller.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hq2808.blog.base.ExpiredException;
import com.hq2808.blog.base.response.Response;
import com.hq2808.blog.controller.BaseController;
import com.hq2808.blog.dto.User;
import com.hq2808.blog.dto.UserDetailDto;
import com.hq2808.blog.dto.UserSignUpDto;
import com.hq2808.blog.service.user.UserService;
import com.hq2808.blog.shared.BusinessException;

@RestController
@RequestMapping("/webapi/user")
public class UserController extends BaseController{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public Response getAll() {
		return Response.build().data(this.userService.getAll());
	}
	
	@GetMapping("/user-detail")
	public Response getUserDetail() throws ExpiredException {
		Optional<User> oUser = this.getCurrentUser();
		if (!oUser.isPresent()) {
			return Response.build().code(Response.CODE_NOT_FOUND);
		}
		UserDetailDto userDetail = UserDetailDto.builder()
				.username(oUser.get().getUsername())
				.displayName(oUser.get().getFullname())
				.role(oUser.get().getRole().getValue())
				.build();
		return Response.build().ok().data(userDetail);
	}
	
	@PostMapping("/sign-up")
	public Response signUp(@RequestBody UserSignUpDto userSignUp) throws BusinessException {
		userSignUp.setPassword(this.passwordEncoder.encode(userSignUp.getPassword()));
		return Response.build().data(this.userService.signup(userSignUp));
	}
}
