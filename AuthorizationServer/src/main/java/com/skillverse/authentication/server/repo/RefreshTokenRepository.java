package com.skillverse.authentication.server.repo;

import com.skillverse.authentication.server.entity.RefreshToken;
import com.skillverse.authentication.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    boolean existsByToken(String token);

    void deleteByToken(String token);

    void deleteAllByUser(User user);
}