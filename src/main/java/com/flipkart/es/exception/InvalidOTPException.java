package com.flipkart.es.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidOTPException extends RuntimeException{
    private String message;
}
