package com.hq2808.blog.service.post;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hq2808.blog.base.request.DataTableRequest;
import com.hq2808.blog.dto.Post;

public interface PostService {
	Optional<Post> getById(String id);
	Page<Post> getAll(DataTableRequest request);
	Post saveAndUpdate(Post post);
	void delete(Post post);
}
