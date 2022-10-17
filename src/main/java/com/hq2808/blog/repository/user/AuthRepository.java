package com.hq2808.blog.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hq2808.blog.entity.user.AuthEntity;
import com.hq2808.blog.enumerate.AuthStatus;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, String>{
	/**
	 * Find by username and status.
	 *
	 * @param username the username
	 * @param status the status
	 * @return the optional
	 */
	Optional<AuthEntity> findByUsernameAndStatus(String username, AuthStatus status);

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the optional
	 */
	Optional<AuthEntity> findByUsername(String username);
}
