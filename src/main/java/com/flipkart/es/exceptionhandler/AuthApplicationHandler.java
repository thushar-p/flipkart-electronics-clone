package com.flipkart.es.exceptionhandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flipkart.es.exception.InvalidUserRoleException;
import com.flipkart.es.exception.UserVerifiedException;

@RestControllerAdvice
public class AuthApplicationHandler {
    
    public ResponseEntity<Object> structure(HttpStatus status, String message, Object rootCause){
        return new ResponseEntity<Object>(Map.of("status", status.value(), "message", message, "root cause", rootCause), status);
    }

    @ExceptionHandler(UserVerifiedException.class)
    public ResponseEntity<Object> handlesUserVerifiedException(UserVerifiedException exception){
        return structure(HttpStatus.CREATED, exception.getMessage(), "the email you entered already exists");
    }

    @ExceptionHandler(InvalidUserRoleException.class)
    public ResponseEntity<Object> handleInvalidUserRoleException(InvalidUserRoleException exception){
        return structure(HttpStatus.BAD_REQUEST, exception.getMessage(), "user not found with the specified user role");
    }
    
}
