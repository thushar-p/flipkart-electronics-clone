package com.flipkart.es.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.es.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	Optional<RefreshToken> findByRefreshToken(String rt);

	List<RefreshToken> findByRefreshTokenExpirationTimeBefore(LocalDateTime now);
    
}
