package com.tuhinal.employeemanagement.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.tuhinal.employeemanagement.dto.EmployeeBasicInfoDto;
import com.tuhinal.employeemanagement.dto.response_dto.EmployeeBasicInfoSearchDto;
import com.tuhinal.employeemanagement.entity.EmployeeBasicInfo;
import com.tuhinal.employeemanagement.entity.QEmployeeBasicInfo;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class EmployeeInfoService {
    
    private final EmployeeInfoRepository employeeInfoRepository;
    private final IdGeneratorService idGeneratorService;
    private final EntityManager entityManager;

    @Transactional
    public EmployeeBasicInfoDto save(EmployeeBasicInfoDto employeeBasicInfoDto) {
        var employeeInfo = copyProp(employeeBasicInfoDto, EmployeeBasicInfo.class);
        employeeInfo.setDob(LocalDate.now());
        EmployeeBasicInfo employeeBasicInfoFromDb = employeeInfoRepository.save(employeeInfo);
        return copyProp(employeeBasicInfoFromDb, EmployeeBasicInfoDto.class);
    }

    public Page<EmployeeBasicInfoDto> search(EmployeeBasicInfoSearchDto employeeBasicInfoSearchDto) {

        final QEmployeeBasicInfo qEmployeeBasicInfo = QEmployeeBasicInfo.employeeBasicInfo;
        final JPAQuery<EmployeeBasicInfo> employeeBasicInfoJPAQuery = new JPAQuery<>(entityManager);

        Predicate predicate = searchPredicate(otRequisitionSearchDto);
        Pageable pageable = PageRequest.of(otRequisitionSearchDto.getPage(), otRequisitionSearchDto.getSize());

        var query = employeeBasicInfoJPAQuery
                .from(qEmployeeBasicInfo)
                .where(predicate)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(qOtRequisition.createdDate.desc());

        return new PageImpl<>(copyList(query.fetch(), EmployeeBasicInfoDto.class), pageable, query.fetchCount());
    }

}
