package com.flipkart.es.serviceimpl;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flipkart.es.enums.UserRole;
import com.flipkart.es.exception.UserNotFoundWithRole;
import com.flipkart.es.repository.CustomerRepository;
import com.flipkart.es.repository.SellerRepository;
import com.flipkart.es.requestdto.UserRequest;
import com.flipkart.es.responsedto.UserResponse;
import com.flipkart.es.service.AuthService;
import com.flipkart.es.util.ResponseStructure;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired 
	private SellerRepository sellerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
		
		UserRole userRole = UserRole.valueOf(userRequest.getUserRole().toUpperCase());
		if(EnumSet.allOf(UserRole.class).contains(userRole)) {
			
		}
		else {
			throw new UserNotFoundWithRole("User not found with the specified role");
		}
		
	}

}
