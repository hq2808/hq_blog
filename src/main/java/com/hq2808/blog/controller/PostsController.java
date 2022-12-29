package com.hq2808.blog.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hq2808.blog.base.ExpiredException;
import com.hq2808.blog.base.request.DataTableRequest;
import com.hq2808.blog.base.response.Response;
import com.hq2808.blog.dto.Post;
import com.hq2808.blog.dto.User;
import com.hq2808.blog.entity.PostEntity;
import com.hq2808.blog.repository.PostRepository;
import com.hq2808.blog.service.post.PostService;

@RestController
@RequestMapping("/webapi/post")
public class PostsController extends BaseController{
	
	@Autowired
	private PostService postsService;
	
	@Autowired
	private PostRepository postsRepo;
	
	@GetMapping
	public Response getAll(DataTableRequest request) {
		return Response.build().data(this.postsService.getAll(request));
	}
	
	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") String id) {
		return Response.build().data(this.postsService.findById(id));
	}
	
	@PostMapping
	public Response save(@Valid @RequestBody Post post) throws ExpiredException {
		Optional<User> oCurrentUser = this.getCurrentUser();
		// if user login not exist
		if (!oCurrentUser.isPresent()) {
			return Response.build().code(Response.CODE_PERMISSION);
		}
		
		switch(oCurrentUser.get().getRole()) {
		case ADMIN: 
			break;
		default:
			return Response.build().code(Response.CODE_PERMISSION);
			
		}
		post.setUser(oCurrentUser.get());
		return Response.build().ok().data(this.postsService.saveAndUpdate(post));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
		Optional<PostEntity> optPosts = this.postsRepo.findById(id);
		if(!optPosts.isPresent()) {
			return null;
		}
		post.setId(id);
		return ResponseEntity.ok().body(this.postsService.saveAndUpdate(post));
	}
}
