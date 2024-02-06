package com.flipkart.es.serviceimpl;

import java.util.EnumSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flipkart.es.entity.Customer;
import com.flipkart.es.entity.Seller;
import com.flipkart.es.entity.User;
import com.flipkart.es.enums.UserRole;
import com.flipkart.es.exception.UserAlreadyRegisteredException;
import com.flipkart.es.exception.UserNotFoundWithRoleException;
import com.flipkart.es.repository.CustomerRepository;
import com.flipkart.es.repository.SellerRepository;
import com.flipkart.es.repository.UserRepository;
import com.flipkart.es.requestdto.UserRequest;
import com.flipkart.es.responsedto.UserResponse;
import com.flipkart.es.service.AuthService;
import com.flipkart.es.util.ResponseStructure;
import com.flipkart.es.util.ResponseEntityProxy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

	private UserRepository userRepository;
	private SellerRepository sellerRepository;
	private CustomerRepository customerRepository;

	@SuppressWarnings({ "unchecked" })
	private <T extends User> T mapToUser(UserRequest userRequest) {

		User user = null;
		switch (UserRole.valueOf(userRequest.getUserRole().toUpperCase())) {
		case SELLER -> {user = new Seller();}
		case CUSTOMER -> {user = new Customer();}	
		}

		user.setUsername(userRequest.getUserEmail().split("@")[0].toString());
		user.setUserEmail(userRequest.getUserEmail());
		user.setUserPassword(userRequest.getUserPassword());
		user.setUserRole(UserRole.valueOf(userRequest.getUserRole().toUpperCase()));
		user.setEmailVerified(false);
		user.setDeleted(false);

		return (T) user;

	}
	
	private UserResponse mapToUserResponse(User user) {

		return UserResponse.builder()
				.userId(user.getUserId())
				.userEmail(user.getUserEmail())
				.username(user.getUsername())
				.userRole(user.getUserRole())
				.isDeleted(user.isDeleted())
				.isEmailVerified(user.isEmailVerified())
				.build();

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {

		UserRole userRole = UserRole.valueOf(userRequest.getUserRole().toUpperCase());
		if(EnumSet.allOf(UserRole.class).contains(userRole)) {
			
			if(userRepository.existsByUserEmail(userRequest.getUserEmail()))
				throw new UserAlreadyRegisteredException("user already registered");

			User user = mapToUser(userRequest);
			if(user.getUserRole().equals(UserRole.SELLER)) {
				Seller seller = (Seller) user;
				sellerRepository.save(seller);
			}
			else {
				Customer customer = (Customer) user;
				customerRepository.save(customer);
			}
			return ResponseEntityProxy.setResponseStructure(HttpStatus.ACCEPTED,
					"user successfully saved",
					mapToUserResponse(user));
		}
		else {
			throw new UserNotFoundWithRoleException("User not found with the specified role");
		}

	}

	

}
