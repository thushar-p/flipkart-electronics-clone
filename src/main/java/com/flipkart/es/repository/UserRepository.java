package com.flipkart.es.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.es.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
