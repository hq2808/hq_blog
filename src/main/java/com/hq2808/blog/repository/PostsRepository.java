package com.hq2808.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hq2808.blog.entity.PostsEntity;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, String>{
	Optional<PostsEntity> findById(String id);
}
