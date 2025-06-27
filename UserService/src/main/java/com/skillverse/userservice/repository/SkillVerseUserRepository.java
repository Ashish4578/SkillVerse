package com.skillverse.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.Role;

public interface SkillVerseUserRepository extends JpaRepository<AppUser, Long> {

	@Query("SELECT u.appUserId FROM AppUser u WHERE u.username = :username")
	Optional<Long> findAppUserIdByUsername(@Param("username") String username);
	
	@Query("SELECT u FROM AppUser u JOIN u.roles r WHERE r = :role")
	List<AppUser> findAllByRole(@Param("role") Role role);
}
