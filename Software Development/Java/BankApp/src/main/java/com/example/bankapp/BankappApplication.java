package com.example.bankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Main application class for the BankApp Spring Boot project
@SpringBootApplication // Enables auto-configuration and component scanning
public class BankappApplication {

    // Entry point of the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(BankappApplication.class, args);
    }

}
