package com.tuhinal.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.entity.EmployeeAccount;
import com.tuhinal.employeemanagement.entity.EmployeeAccountTransaction;
import com.tuhinal.employeemanagement.entity.EmployeeAttendance;
import com.tuhinal.employeemanagement.entity.EmployeeBankInfo;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class EmployeeBasicInfoDto {

    private String id;
    private String firstName;
    private String lastName;
    private String employeeNcId;
    @Enumerated(EnumType.STRING)
    private DesignationTypeEnum designationTypeEnumKey;
    private String designationTypeEnumValue;
    private String phone;
    private String presentAddress;
    private String permanentAddress;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String paymentTypeEnumValue;
    private Double currentSalary;

    private EmployeeAccount employeeAccount;
    private List<EmployeeAccountTransactionDto> employeeAccountTransactionDtoList;
    private EmployeeAttendance employeeAttendance;
    private EmployeeBankInfo employeeBankInfo;
    
    public EmployeeBasicInfoDto(String id) {
        this.id = id;
    }
}
