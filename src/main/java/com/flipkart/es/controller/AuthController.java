package com.flipkart.es.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.es.requestdto.AuthRequest;
import com.flipkart.es.requestdto.OtpModel;
import com.flipkart.es.requestdto.UserRequest;
import com.flipkart.es.responsedto.AuthResponse;
import com.flipkart.es.responsedto.UserResponse;
import com.flipkart.es.service.AuthService;
import com.flipkart.es.util.ResponseEntityProxy;
import com.flipkart.es.util.ResponseStructure;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
	
	private AuthService authService;
	
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest) {
		return authService.registerUser(userRequest);
	}
	
	@PostMapping("/verify-otp")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOtp(@RequestBody OtpModel otpModel){
		return authService.verifyOtp(otpModel);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AuthResponse>> login(@RequestBody AuthRequest authRequest, 
	HttpServletResponse httpServletResponse){
		return authService.login(authRequest, httpServletResponse);
	}

	@PostMapping("/check")
	public ResponseEntity<ResponseStructure<String>> check(){
		return ResponseEntityProxy.setResponseStructure(HttpStatus.OK, 
		"check successfull", 
		"checkpoint reached");
	}
}
