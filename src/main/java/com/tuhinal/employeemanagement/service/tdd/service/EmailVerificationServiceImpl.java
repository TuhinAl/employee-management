package com.tuhinal.employeemanagement.service.tdd.service;

import com.tuhinal.employeemanagement.service.tdd.model.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {
  @Override
  public void scheduleEmailConfirmation(User user) {
// put
    System.out.println("scheduleEmailConfirmation is called");
  }
}
