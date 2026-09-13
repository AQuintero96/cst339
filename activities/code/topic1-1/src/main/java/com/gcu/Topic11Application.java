package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Topic11Application {

    public static void main(String[] args) {
        // I print my greeting before Spring Boot starts.
        System.out.println("Hello World from my Spring Boot application!");

        // I start my Spring Boot application and its embedded web server.
        SpringApplication.run(Topic11Application.class, args);
    }
}