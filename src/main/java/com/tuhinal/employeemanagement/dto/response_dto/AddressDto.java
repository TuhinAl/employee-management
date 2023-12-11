package com.tuhinal.employeemanagement.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AddressDto {

    private String village;
    private String postOffice;
    private String policeStation;
    private String city;
    private String district;
    private String division;

}
