package com.hq2808.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hq2808.blog.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {

}
