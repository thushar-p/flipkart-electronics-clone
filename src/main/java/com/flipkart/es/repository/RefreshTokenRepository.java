package com.flipkart.es.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.es.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    
}
