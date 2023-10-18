package com.tuhinal.employeemanagement.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(3)
public class UserServiceTest {

    @Test
    void userLogin() {
        System.out.println("Customer Logged in successfully");

    }
    @Test
    void userLogout() {
        System.out.println("Customer Logged out.");
    }

}
