package com.flipkart.es.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.flipkart.es.repository.UserRepository;
import com.flipkart.es.serviceimpl.AuthServiceImpl;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ScheduledJobs {

    private UserRepository userRepository;
    private AuthServiceImpl authServiceImpl;

    @Scheduled(fixedDelay = 10000L)
    public void softDeleteNonVerifiedUser() {
        userRepository.findByIsEmailVerified(false)
                .forEach(user -> {
                    user.setDeleted(true);
                    authServiceImpl.saveUser(user);
                });
    }

}
