package com.flipkart.es.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.es.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
