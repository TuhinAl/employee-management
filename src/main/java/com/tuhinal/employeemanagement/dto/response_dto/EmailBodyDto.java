package com.tuhinal.employeemanagement.dto.response_dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class EmailBodyDto {
  private String toEmail;
  private String employeeName;
  private String transactionId;
  private LocalDateTime transactionDate;
  private Double currentSalary;
}

