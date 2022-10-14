package com.hq2808.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hq2808.blog.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String>{
	Optional<PostEntity> findById(String id);
}
