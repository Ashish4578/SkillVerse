package com.skillverse.userservice.repository;

import com.skillverse.userservice.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    // 🔹 Find by username (used in login + Security)
    Optional<AppUser> findByUsername(String username);

    // 🔹 Check duplicate username
    boolean existsByUsername(String username);

    // 🔹 Check duplicate email
    boolean existsByEmail(String email);

    // 🔹 Optional: find by email
    Optional<AppUser> findByEmail(String email);

    @Query("""
    SELECT DISTINCT u 
    FROM AppUser u 
    JOIN u.roles r 
    WHERE r.name IN :roles
""")
    Page<AppUser> findUsersWithRoles(@Param("roles") List<String> roles, Pageable pageable);

}