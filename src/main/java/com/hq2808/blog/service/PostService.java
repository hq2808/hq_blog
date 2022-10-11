package com.hq2808.blog.service;

import java.util.List;

import com.hq2808.blog.dto.Posts;

public interface PostService {
	List<Posts> getAll();
	Posts saveAndUpdate(Posts posts);
}
