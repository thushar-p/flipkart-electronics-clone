package com.flipkart.es.util;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flipkart.es.exception.UserAlreadyRegisteredException;
import com.flipkart.es.exception.UserNotFoundWithRoleException;

@RestControllerAdvice
public class ApplicationHandler {
    
    public ResponseEntity<Object> structure(HttpStatus status, String message, Object rootCause){
        return new ResponseEntity<Object>(Map.of("status", status.value(), "message", message, "root cause", rootCause), status);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException exception){
        return structure(HttpStatus.BAD_REQUEST, exception.getMessage(), "the email you entered already exists");
    }

    @ExceptionHandler(UserNotFoundWithRoleException.class)
    public ResponseEntity<Object> handleUserNotFoundWithRoleException(UserNotFoundWithRoleException exception){
        return structure(HttpStatus.NOT_FOUND, exception.getMessage(), "user not found with the specified user role");
    }
    
}
