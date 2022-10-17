package com.hq2808.blog.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hq2808.blog.entity.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	Optional<UserEntity> findByUsername(String username);
	
	/**
	 * Find by auth id.
	 *
	 * @param authId the auth id
	 * @return the optional
	 */
	Optional<UserEntity> findByAuthId(String authId);
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<UserEntity> findById(Long id);

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<UserEntity> findByEmail(String email);
	
	Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
