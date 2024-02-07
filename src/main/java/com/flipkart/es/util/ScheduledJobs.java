package com.flipkart.es.util;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.flipkart.es.entity.User;
import com.flipkart.es.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ScheduledJobs {

    private UserRepository userRepository;

    @SuppressWarnings("null")
    @Scheduled(cron = "0 0 0 * * MON-SUN")
    public void deleteNonVerifiedUser() {
        List<User> listOfNonVerifiedUsers = userRepository.findByIsEmailVerified(false);
        userRepository.deleteAll(listOfNonVerifiedUsers);
    }

}
