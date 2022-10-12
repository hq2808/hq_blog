package com.hq2808.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hq2808.blog.dto.Posts;
import com.hq2808.blog.entity.PostsEntity;
import com.hq2808.blog.repository.PostsRepository;
import com.hq2808.blog.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostsController {
	
	@Autowired
	private PostService postsService;
	
	@Autowired
	private PostsRepository postsRepo;
	
	@GetMapping
	public ResponseEntity<List<Posts>> getAll() {
		return ResponseEntity.ok().body(this.postsService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<Posts> save(@RequestBody Posts posts) {
		return ResponseEntity.ok().body(this.postsService.saveAndUpdate(posts));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Posts> update(@PathVariable String id, @RequestBody Posts posts) {
		Optional<PostsEntity> optPosts = this.postsRepo.findById(id);
		if(optPosts.isEmpty()) {
			return null;
		}
		posts.setId(id);
		return ResponseEntity.ok().body(this.postsService.saveAndUpdate(posts));
	}
}
