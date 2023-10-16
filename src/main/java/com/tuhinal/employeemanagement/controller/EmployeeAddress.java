package com.tuhinal.employeemanagement.controller;

import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeAddress {
    
    @GetMapping("/address")
    public ResponseEntity<UserResponse> profile() {
        return ResponseEntity.ok(new UserResponse("you Address", "message"));
    }
}
