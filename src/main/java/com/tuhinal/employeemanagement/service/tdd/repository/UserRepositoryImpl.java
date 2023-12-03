package com.tuhinal.employeemanagement.service.tdd.repository;

import com.tuhinal.employeemanagement.service.tdd.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    Map<String, User> userDb = new HashMap<>();

    @Override
    public boolean save(User user) {
        boolean returnValue = false;
        if (!userDb.containsKey(user.getId())) {
            userDb.put(user.getId(), user);
            returnValue = true;
        }
        return returnValue;
    }
}
