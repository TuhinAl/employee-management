package com.tuhinal.employeemanagement.service.tdd.service;

import com.tuhinal.employeemanagement.service.tdd.User;
import org.apache.logging.log4j.util.Strings;

import java.util.UUID;

public class UserServiceImpl implements UserService {


    @Override
    public User createUser(
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String repeatPassword) {
        if (Strings.isNotBlank(firstName) || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("User's firstName is missing");
        }

        return new User(UUID.randomUUID().toString(), firstName,lastName, email);

    }
}
