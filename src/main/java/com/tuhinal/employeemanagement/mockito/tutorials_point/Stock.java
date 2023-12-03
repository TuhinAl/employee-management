package com.tuhinal.employeemanagement.mockito.tutorials_point;

import lombok.Data;

@Data
public class Stock {
    private String stockId;
    private String name;
    private int quantity;

    public Stock(String stockId, String name, int quantity) {
        this.stockId = stockId;
        this.name = name;
        this.quantity = quantity;
    }
}
