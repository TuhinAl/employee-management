package com.tuhinal.employeemanagement.service;


import com.tuhinal.employeemanagement.dto.EmployeeBasicInfoDto;
import com.tuhinal.employeemanagement.entity.EmployeeBasicInfo;
import com.tuhinal.employeemanagement.repository.EmployeeInfoRepository;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class EmployeeInfoService {
    
    private final EmployeeInfoRepository employeeInfoRepository;
    private final IdGeneratorService idGeneratorService;
    
    @Transactional
    public EmployeeBasicInfoDto save(EmployeeBasicInfoDto employeeBasicInfoDto) {
        var employeeInfo = copyProp(employeeBasicInfoDto, EmployeeBasicInfo.class);
//        employeeInfo.setEnabled(Boolean.TRUE);
        employeeInfo.setDob(LocalDate.now());
//        employeeInfo.setEmployeeNcId(idGeneratorService.employeeIdGenerator());
        EmployeeBasicInfo employeeBasicInfoFromDb = employeeInfoRepository.save(employeeInfo);
        return copyProp(employeeBasicInfoFromDb, EmployeeBasicInfoDto.class);
    }

/*
    public Page<EmployeeInfoDto> search(EmployeeInfoSearchDto employeeInfoSearchDto) {

        final QOtRequisition qOtRequisition = QOtRequisition.otRequisition;
        final JPAQuery<OtRequisition> otRequisitionJPAQuery = new JPAQuery<>(entityManager);

        Predicate predicate = searchPredicate(otRequisitionSearchDto);
        Pageable pageable = PageRequest.of(otRequisitionSearchDto.getPage(), otRequisitionSearchDto.getSize());

        var query = otRequisitionJPAQuery
                .from(qOtRequisition)
                .where(predicate)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(qOtRequisition.createdDate.desc());

        return new PageImpl<>(copyList(query.fetch(), EmployeeInfoDto.class), pageable, query.fetchCount());
    }
*/

}
