package com.tuhinal.employeemanagement.util;


import com.querydsl.jpa.impl.JPAQuery;
import com.tuhinal.employeemanagement.entity.EmployeeBasicInfo;
import com.tuhinal.employeemanagement.entity.QEmployeeBasicInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class IdGeneratorService {
    
    private final QEmployeeBasicInfo qEmployeeBasicInfo = QEmployeeBasicInfo.employeeBasicInfo;
    private final EntityManager entityManager;
    
    public String employeeIdGenerator() {
        final JPAQuery<EmployeeBasicInfo> jpaQuery = new JPAQuery<>(entityManager);
        var employeeInfoList = jpaQuery.from(qEmployeeBasicInfo).fetch();
        return getFormattedId("EMP01", employeeInfoList.size());
    }
    
    
    public String getFormattedId(String prefix, Integer count) {
        var yearValue = LocalDate.now().getYear();
        var monthValue = StringUtils
                .leftPad(String.valueOf(LocalDate.now().getMonthValue()), 2, '0');
        return new StringBuilder(prefix)
                .append(yearValue)
                .append(monthValue)
                .append(StringUtils.leftPad(String.valueOf(count + 1), 6, '0'))
                .toString();
    }
    
    
    public String empIdGenerator() {
        synchronized (this) {
            LocalDate localDate = LocalDate.now();
            String year = localDate.format(DateTimeFormatter.ofPattern("yy"));
            String month = localDate.format(DateTimeFormatter.ofPattern("MM"));
            String prefixEmployeeId = "EMPO1" + "01" + year + month;
            EmployeeBasicInfo employeeBasicInfo = this.empId(prefixEmployeeId);
            String newEmpId;
            if (null != employeeBasicInfo) {
                newEmpId = prefixEmployeeId + StringUtil
                        .joinerStringLastPartIncrement(employeeBasicInfo.getEmployeeNcId(), 6, 1);
            } else {
                newEmpId = prefixEmployeeId + StringUtil.intToZeroAddedString(1, 6);
            }
            return newEmpId;
        }
    }
    
    public EmployeeBasicInfo empId(String employeeNcId) {
        synchronized (this) {
            final QEmployeeBasicInfo qEmployeeBasicInfo = QEmployeeBasicInfo.employeeBasicInfo;
            final JPAQuery<EmployeeBasicInfo> query = new JPAQuery<>(entityManager);
            return query.from(qEmployeeBasicInfo)
                    .where(qEmployeeBasicInfo.employeeNcId.like("%" + employeeNcId + "%"))
                    .orderBy(qEmployeeBasicInfo.employeeNcId.desc())
                    .fetchFirst();
        }
    }
}
