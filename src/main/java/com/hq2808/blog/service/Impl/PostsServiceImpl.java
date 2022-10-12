package com.hq2808.blog.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq2808.blog.dto.Posts;
import com.hq2808.blog.entity.PostsEntity;
import com.hq2808.blog.repository.PostsRepository;
import com.hq2808.blog.service.PostService;

@Service
public class PostsServiceImpl implements PostService{
	
	@Autowired
	private PostsRepository repo;

	@Override
	public List<Posts> getAll() {
		return this.repo.findAll().stream().map(PostsEntity::toDomain).collect(Collectors.toList());
	}

	@Override
	public Posts saveAndUpdate(Posts posts) {
		return this.repo.save(PostsEntity.toEntity(posts)).toDomain();
	}

}
