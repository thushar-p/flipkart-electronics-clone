package com.flipkart.es.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OTPExpiredException extends RuntimeException{

    private String message;

}
