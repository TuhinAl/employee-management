package com.tuhinal.employeemanagement.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.tuhinal.employeemanagement.dto.response_dto.EmployeeBasicInfoSearchDto;
import com.tuhinal.employeemanagement.entity.QEmployeeBasicInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class EmployeeBasicInfoPredicate {

  private static final QEmployeeBasicInfo qEmployeeBasicInfo = QEmployeeBasicInfo.employeeBasicInfo;

  public static BooleanBuilder searchPredicate(EmployeeBasicInfoSearchDto employeeBasicInfoSearchDto) {

    BooleanBuilder builder = new BooleanBuilder();
    if (!CollectionUtils.isEmpty(employeeBasicInfoSearchDto.getIdList())) {
      builder.and(qEmployeeBasicInfo.id.in(employeeBasicInfoSearchDto.getIdList()));
    }

    if (StringUtils.isNotBlank(employeeBasicInfoSearchDto.getMultiSearchProp())) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      booleanBuilder.or(qEmployeeBasicInfo.firstName.containsIgnoreCase(employeeBasicInfoSearchDto.getMultiSearchProp()))
        .or(qEmployeeBasicInfo.lastName.containsIgnoreCase(employeeBasicInfoSearchDto.getMultiSearchProp()))
        .or(qEmployeeBasicInfo.employeeNcId.containsIgnoreCase(employeeBasicInfoSearchDto.getMultiSearchProp()));
      builder.and(booleanBuilder);
    }
/*
    if (Objects.nonNull(employeeBasicInfoSearchDto.getRequisitionRequestAtFrom()) &&
      Objects.nonNull(employeeBasicInfoSearchDto.getRequisitionRequestAtTo())) {
      builder.and(qEmployeeBasicInfo.requisitionRequestAt.between(employeeBasicInfoSearchDto.getRequisitionRequestAtFrom(),
        employeeBasicInfoSearchDto.getRequisitionRequestAtTo()));
    }*/

    if (StringUtils.isNotBlank(employeeBasicInfoSearchDto.getEmployeeNcId())) {
      builder.and(qEmployeeBasicInfo.employeeNcId.containsIgnoreCase(employeeBasicInfoSearchDto.getEmployeeNcId()));
    }

    return builder;
  }
}
