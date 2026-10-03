package com.gcu.business;

import org.springframework.stereotype.Service;

/**
 * Demonstrates a business service discovered through component scanning.
 */
@Service
public class SecurityBusinessService {

    /**
     * Prints a test message and accepts the credentials for this exercise.
     *
     * @param username the submitted username
     * @param password the submitted password
     * @return true for this demonstration
     */
    public boolean authenticate(String username, String password) {
        System.out.println("Hello from the SecurityBusinessService");
        return true;
    }
}