package com.flipkart.es.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.es.entity.AccessToken;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long>{

    Optional<AccessToken> findByAccessToken(String at);
    
}
