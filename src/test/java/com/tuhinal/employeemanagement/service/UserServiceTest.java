package com.tuhinal.employeemanagement.service;

import com.tuhinal.employeemanagement.service.tdd.repository.UserRepository;
import com.tuhinal.employeemanagement.service.tdd.service.UserService;
import com.tuhinal.employeemanagement.service.tdd.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//@Order(3)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userRepository);
        firstName = "Alauddin";
        lastName = "Tuhin";
        email = "alauddintuhin1411@gmail.com";
        password = "12345678";
        repeatPassword = "12345678";
    }


//    @Test
//    void userLogin() {
//        System.out.println("Customer Logged in successfully");
//
//    }
//    @Test
//    void userLogout() {
//        System.out.println("Customer Logged out.");
//     }

}
