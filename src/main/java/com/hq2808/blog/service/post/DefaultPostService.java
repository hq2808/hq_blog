package com.hq2808.blog.service.post;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hq2808.blog.dto.Post;
import com.hq2808.blog.entity.PostEntity;
import com.hq2808.blog.repository.PostRepository;

@Service
public class DefaultPostService implements PostService{
	
	@Autowired
	private PostRepository repo;

	@Override
	public List<Post> getAll() {
		return this.repo.findAll().stream().map(PostEntity::toDomain).collect(Collectors.toList());
	}

	@Override
	public Post saveAndUpdate(Post post) {
		return this.repo.save(PostEntity.toEntity(post)).toDomain();
	}

	@Override
	public Optional<Post> getById(String id) {
		return Optional.ofNullable(this.repo.findById(id).get().toDomain()) ;
	}

	@Override
	public void delete(Post post) {
		this.repo.delete(PostEntity.toEntity(post));
	}

}
