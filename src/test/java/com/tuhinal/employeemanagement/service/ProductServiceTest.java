package com.tuhinal.employeemanagement.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
@Order(2)
public class ProductServiceTest {

    @Test
    void addProduct() {
        System.out.println("Product Added.");

    }
    @Test
    void removedProduct() {
        System.out.println("Product Removed.");
    }

}
