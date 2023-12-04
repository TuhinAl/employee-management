package com.tuhinal.employeemanagement.controller;


import com.tuhinal.employeemanagement.dto.EmployeeBasicInfoDto;
import com.tuhinal.employeemanagement.service.EmployeeInfoService;
import com.tuhinal.employeemanagement.util.ApiResponse;
import com.tuhinal.employeemanagement.util.ApiResponseEntityFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee-info")
@Slf4j
@AllArgsConstructor
public class EmployeeInfoController {
    
    private final ApiResponseEntityFactory responseFactory;
    private final EmployeeInfoService employeeInfoService;
    
    @PostMapping()
    public ResponseEntity<ApiResponse<EmployeeBasicInfoDto>> save(@RequestBody EmployeeBasicInfoDto employeeBasicInfoDto) {
        return responseFactory.saveResponse(employeeInfoService.save(employeeBasicInfoDto));
    }
    @GetMapping()
    public ResponseEntity<ApiResponse<EmployeeBasicInfoDto>> getData() {
        return responseFactory.saveResponse("Your Data");
    }
    
}