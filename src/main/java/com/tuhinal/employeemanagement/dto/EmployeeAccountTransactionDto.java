package com.tuhinal.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.entity.EmployeeBasicInfo;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class EmployeeAccountTransactionDto {
    
    private String id;
    private String transactionNcId;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime transactionDateTime;
    
    private EmployeeBasicInfoDto employeeBasicInfoDto;
    private String employeeBasicInfoId;
    private Boolean enabled;

    public EmployeeAccountTransactionDto(String id) {
        this.id = id;
    }
}
