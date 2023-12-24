package com.tuhinal.employeemanagement.service;

import com.tuhinal.employeemanagement.dto.response_dto.EmailBodyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender javaMailSender;
  private final TemplateEngine templateEngine;
  public static final String SUBJECT = "Salary Disbursement Email";
  public void sendHtmlEmail(EmailBodyDto emailBodyDto) {

    //todo need to add salary month
    String body = "Dear Mr./Mrs " + emailBodyDto.getEmployeeName() + " your Salary BDT "+emailBodyDto.getCurrentSalary()
    +" for the month has been credited, Transaction Id: " + emailBodyDto.getTransactionId()
      + " and date: " + emailBodyDto.getTransactionDate();

    // Create Thymeleaf context with variables for the template
    Context context = new Context();
    context.setVariable("body", body);

    String htmlBody = templateEngine.process("email-template", context);

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(emailBodyDto.getToEmail());
    message.setSubject(SUBJECT);
    message.setText(htmlBody);
    message.setFrom("alauddintuhin1411@gmail.com");

    javaMailSender.send(message);
  }
}
