package com.tuhinal.employeemanagement.dto.response_dto;


import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EmployeeBasicSearchDto extends SearchDto {

  private List<String> idList;
  private List<String> idNotEqualList;
  private String id;
  private String firstName;
  private String lastName;
  private String employeeNcId;
  @Enumerated(EnumType.STRING)
  private DesignationTypeEnum designationTypeEnumKey;

  private String multiSearchProp;

  private Boolean enabled;

}
