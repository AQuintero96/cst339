package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts the IT Parts Inventory Manager application.
 */
@SpringBootApplication
public class ItPartsInventoryApplication {

    /**
     * Launches Spring Boot and the embedded web server.
     *
     * @param args command-line arguments supplied at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(ItPartsInventoryApplication.class, args);
    }
}