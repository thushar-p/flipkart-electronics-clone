package com.flipkart.es.entity;

import com.flipkart.es.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
    private int userId;
    private String username;
    private String userEmail;
    private String userPassword;
    private UserRole userRole;
    private boolean isEmailVerified;
    private boolean isDeleted;
}
