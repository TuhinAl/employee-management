package com.tuhinal.employeemanagement.dto.response_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.dto.EmployeeAccountDto;
import com.tuhinal.employeemanagement.dto.EmployeeAccountTransactionDto;
import com.tuhinal.employeemanagement.dto.EmployeeAttendanceDto;
import com.tuhinal.employeemanagement.dto.EmployeeBankInfoDto;
import com.tuhinal.employeemanagement.dto.SearchDto;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class EmployeeBasicInfoSearchDto extends SearchDto {
    
    private String id;
    private String name;
    private String employeeNcId;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private DesignationTypeEnum designationTypeEnumKey;
    private String designationTypeEnumValue;

    private EmployeeAccountDto employeeAccountDto;
    private EmployeeAccountTransactionDto employeeAccountTransactionDto;
    private EmployeeAttendanceDto employeeAttendanceDto;
    private EmployeeBankInfoDto employeeBankInfoDto;
    private Boolean enabled;
    
    public EmployeeBasicInfoSearchDto(String id) {
        this.id = id;
    }
}
