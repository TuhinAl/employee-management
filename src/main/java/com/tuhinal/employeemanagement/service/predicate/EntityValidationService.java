package com.tuhinal.employeemanagement.service.predicate;


import com.tuhinal.employeemanagement.entity.EmployeeBasicInfo;
import com.tuhinal.employeemanagement.repository.EmployeeBasicInfoRepository;
import com.tuhinal.employeemanagement.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EntityValidationService {

  private final EmployeeBasicInfoRepository employeeBasicInfoRepository;


  public EmployeeBasicInfo validateEmployeeBasicInfo(String id) {
    Objects.requireNonNull(id);
    return employeeBasicInfoRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(String
        .format("Not found with id: [%s]", id)));
  }

}
