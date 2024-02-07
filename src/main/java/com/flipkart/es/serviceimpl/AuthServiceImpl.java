package com.flipkart.es.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flipkart.es.entity.Customer;
import com.flipkart.es.entity.Seller;
import com.flipkart.es.entity.User;
import com.flipkart.es.enums.UserRole;
import com.flipkart.es.exception.InvalidUserRoleException;
import com.flipkart.es.exception.UserVerifiedException;
import com.flipkart.es.repository.CustomerRepository;
import com.flipkart.es.repository.SellerRepository;
import com.flipkart.es.repository.UserRepository;
import com.flipkart.es.requestdto.UserRequest;
import com.flipkart.es.responsedto.UserResponse;
import com.flipkart.es.service.AuthService;
import com.flipkart.es.util.ResponseEntityProxy;
import com.flipkart.es.util.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	private SellerRepository sellerRepository;
	private CustomerRepository customerRepository;
	private PasswordEncoder passwordEncoder;

	@SuppressWarnings("unchecked")
	private <T extends User> T mapToRespectiveType(UserRequest userRequest) {

		User user = null;
		switch (UserRole.valueOf(userRequest.getUserRole().toUpperCase())) {
			case SELLER -> {
				user = new Seller();
			}
			case CUSTOMER -> {
				user = new Customer();
			}
			default -> throw new InvalidUserRoleException("User not found with the specified role");
		}

		user.setUsername(userRequest.getUserEmail().split("@")[0].toString());
		user.setUserEmail(userRequest.getUserEmail());
		user.setUserPassword(passwordEncoder.encode(userRequest.getUserPassword()));
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

	public User saveUser(User user) {

		if (user.getUserRole().equals(UserRole.SELLER)) {
			Seller seller = (Seller) user;
			return sellerRepository.save(seller);
		} else {
			Customer customer = (Customer) user;
			return customerRepository.save(customer);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {

		

		// if (!UserRole.CUSTOMER.name().equals(userRequest.getUserRole().toUpperCase())) {
		// 	throw new InvalidUserRoleException("invalid user role");
		// }
		// if (!UserRole.SELLER.name().equals(userRequest.getUserRole().toUpperCase())) {
		// 	throw new InvalidUserRoleException("invalid user role");
		// }
		
		if (userRepository.existsByUserEmailAndIsEmailVerified(userRequest.getUserEmail(), true))
			throw new UserVerifiedException("user already registered and verified");

		User user = mapToRespectiveType(userRequest);

		if (userRepository.existsByUserEmail(userRequest.getUserEmail())) {
			// send otp and verify otp
			// setEmailVerified as true and save it.
		} else {
			user = saveUser(user);
		}
		return ResponseEntityProxy.setResponseStructure(HttpStatus.ACCEPTED,
				"user successfully saved",
				mapToUserResponse(user));
	}

}
