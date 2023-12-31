package com.tuhinal.employeemanagement.service.tdd.service;

import com.tuhinal.employeemanagement.service.tdd.model.User;
import com.tuhinal.employeemanagement.service.tdd.repository.UserRepository;

import java.util.UUID;

public class UserServiceImpl implements UserService {

  UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User createUser(
    String firstName,
    String lastName,
    String email,
    String password,
    String repeatPassword) {

    if (firstName == null || firstName.trim().length() == 0) {
      throw new IllegalArgumentException("User's firstName is missing");
    }

    if (lastName == null || lastName.trim().length() == 0) {
      throw new IllegalArgumentException("User's lastName is missing");
    }

    User user = new User(UUID.randomUUID().toString(), firstName, lastName, email);
    boolean isUserCreated;
    try {
      isUserCreated = userRepository.save(user);
    } catch (RuntimeException runtimeException) {
      throw new UserServiceException(runtimeException.getMessage());
    }
    if (!isUserCreated) {
      throw new UserServiceException("Could not create User.");
    }
    return user;

  }
}
