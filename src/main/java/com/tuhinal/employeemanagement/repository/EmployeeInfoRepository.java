package com.tuhinal.employeemanagement.repository;

import com.tuhinal.employeemanagement.entity.EmployeeBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInfoRepository
        extends JpaRepository<EmployeeBasicInfo, String>, QuerydslPredicateExecutor<EmployeeBasicInfo> {
    
    
}
