package com.flipkart.es.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userEmail;
    private String userPassword;
    private String userRole;
}
