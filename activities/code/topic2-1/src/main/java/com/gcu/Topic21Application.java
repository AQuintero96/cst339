package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Start the Spring Boot application for Activity 2, Part 1.
 */
@SpringBootApplication
@ComponentScan({ "com.gcu" })
public class Topic21Application {

    /**
     * Launch the application and its embedded web server.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Topic21Application.class, args);
    }
}