package com.tuhinal.employeemanagement.auth_controller;


import com.tuhinal.employeemanagement.dto.EmployeeAccountDto;
import com.tuhinal.employeemanagement.security.jwt.UserRequest;
import com.tuhinal.employeemanagement.security.jwt.UserResponse;
import com.tuhinal.employeemanagement.service.EmployeeAuthService;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping(path = "/api")
@CrossOrigin("http://localhost:4240")
@AllArgsConstructor
public class EmployeeAuthController {
    
    private final ApiResponseEntityFactory responseFactory;
    private final EmployeeAuthService employeeAuthService;
    
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody EmployeeAccountDto employeeAccountDto) {
        return responseFactory.saveResponse(employeeAuthService.register(employeeAccountDto));
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) throws IOException {
        return employeeAuthService.login(userRequest);
    }
    
 /*   @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return responseFactory.saveResponse(employeeAccountService.logout());
    }*/
    
}