package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeBasicInfoDto;
import com.tuhinal.employeemanagement.service.EmployeeSalaryDisburseService;
import com.tuhinal.employeemanagement.util.ApiResponse;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee-salary-disburse")
@Slf4j
@AllArgsConstructor
public class EmployeeSalaryDisburseController {

  private final ApiResponseEntityFactory responseFactory;
  private final EmployeeSalaryDisburseService employeeSalaryDisburseService;

  @PostMapping()
  public ResponseEntity<ApiResponse<EmployeeBasicInfoDto>> salaryDisburse(@RequestBody EmployeeBasicInfoDto employeeBasicInfoDto) {
    return responseFactory.saveResponse(employeeSalaryDisburseService.salaryDisburse(employeeBasicInfoDto));
  }

}