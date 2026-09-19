package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starts the forms and validation application for Activity 2.
 */
@SpringBootApplication
@ComponentScan({ "com.gcu" })
public class Topic22Application {

    /**
     * Launches the application and its embedded web server.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Topic22Application.class, args);
    }
}