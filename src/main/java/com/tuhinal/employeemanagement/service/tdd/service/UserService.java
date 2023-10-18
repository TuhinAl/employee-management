package com.tuhinal.employeemanagement.service.tdd.service;

import com.tuhinal.employeemanagement.service.tdd.User;

public interface UserService {
    User createUser(String id,
                    String firstName,
                    String lastName,
                    String email,
                    String password,
                    String repeatPassword);
}
