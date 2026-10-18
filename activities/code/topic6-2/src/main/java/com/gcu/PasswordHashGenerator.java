package com.gcu;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Generates a BCrypt hash for the activity's demonstration password.
 * Run directly as a Java Application, not as a Spring Boot application.
 */
public class PasswordHashGenerator {

    /**
     * Prints a hash for the demonstration password test.
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("test"));
    }
}