package com.tuhinal.employeemanagement.service.tdd.service;

import com.tuhinal.employeemanagement.service.tdd.model.User;
import com.tuhinal.employeemanagement.service.tdd.repository.UserRepository;
import com.tuhinal.employeemanagement.service.tdd.repository.UserRepositoryImpl;
import org.apache.logging.log4j.util.Strings;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

        if (Strings.isNotBlank(lastName) || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("User's lastName is missing");
        }

        User user = new User(UUID.randomUUID().toString(), firstName, lastName, email);
        boolean isUserCreated = userRepository.save(user);
        if (!isUserCreated) {
            throw new IllegalArgumentException();
        }
        return user;

    }
}
