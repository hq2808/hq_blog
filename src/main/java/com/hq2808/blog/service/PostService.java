package com.hq2808.blog.service;

import java.util.List;
import java.util.Optional;

import com.hq2808.blog.dto.Post;

public interface PostService {
	Optional<Post> getById(String id);
	List<Post> getAll();
	Post saveAndUpdate(Post post);
	void delete(Post post);
}
