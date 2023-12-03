package com.tuhinal.employeemanagement.service.tdd.repository;

import com.tuhinal.employeemanagement.service.tdd.model.User;

public interface UserRepository {
    boolean save(User user);
}
