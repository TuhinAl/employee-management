package com.tuhinal.employeemanagement.service;


import com.tuhinal.employeemanagement.dto.EmployeeBasicInfoDto;
import com.tuhinal.employeemanagement.dto.response_dto.EmailBodyDto;
import com.tuhinal.employeemanagement.entity.EmployeeAccountTransaction;
import com.tuhinal.employeemanagement.entity.EmployeeBasicInfo;
import com.tuhinal.employeemanagement.repository.EmployeeAccountTransactionRepository;
import com.tuhinal.employeemanagement.repository.EmployeeBasicInfoRepository;
import com.tuhinal.employeemanagement.service.predicate.EntityValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class EmployeeSalaryDisburseService {

  private final EmailService emailService;
  private final EmployeeAccountTransactionRepository employeeAccountTransactionRepository;
  private final EntityValidationService entityValidationService;

  private static final String ALLOWED_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static final int TRANSACTION_ID_LENGTH = 12;
  private static final Double HOUSE_RENT_PERCENTAGE = .30d;
  private static final Double MEDICAL_PERCENTAGE = .05d;
  private static final Double OTHER_ALLOWANCE_PERCENTAGE = .05d;

  @Transactional
  public EmployeeBasicInfoDto salaryDisburse(EmployeeBasicInfoDto employeeBasicInfoDto) {
    EmployeeBasicInfo employeeBasicInfo = entityValidationService.validateEmployeeBasicInfo(employeeBasicInfoDto.getId());

    Double salary = salaryCalculation(employeeBasicInfo);
    EmployeeAccountTransaction employeeAccountTransaction = new EmployeeAccountTransaction();
    String txId = generateRandomString(TRANSACTION_ID_LENGTH);
    employeeAccountTransaction.setEmployeeBasicInfo(new EmployeeBasicInfo(employeeBasicInfo.getId()));
    employeeAccountTransaction.setTransactionId(txId);
    employeeAccountTransaction.setTransactionAmount(salary);
    employeeAccountTransaction.setTransactionDateTime(LocalDateTime.now());
    EmployeeAccountTransaction transaction = employeeAccountTransactionRepository.save(employeeAccountTransaction);
    EmailBodyDto emailBodyDto = new EmailBodyDto();
    emailBodyDto.setToEmail(employeeBasicInfo.getEmployeeAccount().getEmail());
    emailBodyDto.setEmployeeName(employeeBasicInfo.getFirstName() + " " + employeeBasicInfo.getLastName());
    emailBodyDto.setCurrentSalary(transaction.getTransactionAmount());
    emailBodyDto.setTransactionDate(transaction.getTransactionDateTime());
    emailService.sendHtmlEmail(emailBodyDto);
//    EmployeeBasicInfo employeeBasicInfoFromDb = employeeBasicInfoRepository.save(employeeBasicInfo);
    return copyProp(employeeBasicInfoDto, EmployeeBasicInfoDto.class);
  }

  private Double salaryCalculation(EmployeeBasicInfo employeeBasicInfo) {
    Double basicSalary = employeeBasicInfo.getCurrentSalary();
    Double homeRent = basicSalary * HOUSE_RENT_PERCENTAGE;
    Double medicalBonus = basicSalary * MEDICAL_PERCENTAGE;
    Double otherAllowance = basicSalary * OTHER_ALLOWANCE_PERCENTAGE;
    Double grossSalary = basicSalary + homeRent + medicalBonus + otherAllowance;
    return grossSalary;
  }

  public static String generateRandomString(int length) {
    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
      sb.append(ALLOWED_CHARACTERS.charAt(randomIndex));
    }

    return sb.toString();
  }

}
