package com.tuhinal.employeemanagement.service.tdd.model;

import lombok.Getter;

@Getter
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;

    public User(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
