package com.hq2808.blog.service.post;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hq2808.blog.base.request.DataTableRequest;
import com.hq2808.blog.dto.Post;
import com.hq2808.blog.entity.PostEntity;
import com.hq2808.blog.repository.PostRepository;

@Service
public class DefaultPostService implements PostService{
	
	@Autowired
	private PostRepository repo;

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

	@Override
	public Page<Post> getAll(DataTableRequest request) {
		Pageable pageable = request.toPageable();
		return this.repo.findAll(pageable).map(PostEntity::toDomain);
	}

}
