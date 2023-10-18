package com.tuhinal.employeemanagement.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@TestMethodOrder(MethodOrderer.MethodName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(1)
public class OrderServiceTest {

    @Test
//    @Order(1)
    void placeOrder() {
        System.out.println("Order has been placed successfully");

    }
    @Test
//    @Order(3)
    void cancelOrder() {
        System.out.println("Order canceled.");
    }
    @Test
//    @Order(2)
    void pending() {
        System.out.println("Order Pending.");
    }
}
