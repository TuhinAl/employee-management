package com.tuhinal.employeemanagement.dto.response_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AddressDto {

    private String village;
    private String postOffice;
    private String policeStation;
    private String city;
    private String district;
    private String division;

}
