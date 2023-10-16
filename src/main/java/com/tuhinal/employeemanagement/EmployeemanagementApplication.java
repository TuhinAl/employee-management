package com.tuhinal.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.tuhinal.employeemanagement.entity")
public class EmployeemanagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EmployeemanagementApplication.class, args);
//        Will secure any request with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Started EmployeemanagementApplication in 3.10
    }
    
}
