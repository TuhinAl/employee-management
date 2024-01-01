package com.tuhinal.employeemanagement.service.tdd.service;

import com.tuhinal.employeemanagement.service.tdd.model.User;

public interface EmailVerificationService {
  void scheduleEmailConfirmation(User user);
}
