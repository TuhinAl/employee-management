package com.tuhinal.employeemanagement.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.tuhinal.employeemanagement.dto.EmployeeBasicInfoDto;
import com.tuhinal.employeemanagement.dto.response_dto.EmployeeBasicInfoSearchDto;
import com.tuhinal.employeemanagement.entity.EmployeeAccount;
import com.tuhinal.employeemanagement.entity.EmployeeBasicInfo;
import com.tuhinal.employeemanagement.entity.QEmployeeBasicInfo;
import com.tuhinal.employeemanagement.repository.EmployeeBasicInfoRepository;
import com.tuhinal.employeemanagement.util.IdGeneratorService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tuhinal.employeemanagement.service.predicate.EmployeeBasicInfoPredicate.searchPredicate;
import static com.tuhinal.employeemanagement.util.TransformUtil.copyList;
import static com.tuhinal.employeemanagement.util.TransformUtil.copyProp;


@Service
@RequiredArgsConstructor
public class EmployeeInfoService {

  private final EmployeeBasicInfoRepository employeeBasicInfoRepository;
  private final IdGeneratorService idGeneratorService;
  private final EntityManager entityManager;

  @Transactional
  public EmployeeBasicInfoDto save(EmployeeBasicInfoDto employeeBasicInfoDto) {
    var employeeBasicInfo = copyProp(employeeBasicInfoDto, EmployeeBasicInfo.class);
    if (StringUtils.isNotEmpty(employeeBasicInfoDto.getId())) {
      employeeBasicInfo.setEmployeeAccount(new EmployeeAccount(employeeBasicInfoDto.getId()));
    }
    employeeBasicInfo.setEmployeeNcId(idGeneratorService.employeeIdGenerator());

    EmployeeBasicInfo employeeBasicInfoFromDb = employeeBasicInfoRepository.save(employeeBasicInfo);
    return copyProp(employeeBasicInfoFromDb, EmployeeBasicInfoDto.class);
  }

  public Page<EmployeeBasicInfoDto> search(EmployeeBasicInfoSearchDto employeeBasicInfoSearchDto) {

    final QEmployeeBasicInfo qEmployeeBasicInfo = QEmployeeBasicInfo.employeeBasicInfo;
    final JPAQuery<EmployeeBasicInfo> employeeBasicInfoJPAQuery = new JPAQuery<>(entityManager);

    Predicate predicate = searchPredicate(employeeBasicInfoSearchDto);
    Pageable pageable = PageRequest.of(employeeBasicInfoSearchDto.getPage(),
      employeeBasicInfoSearchDto.getSize());

    var query = employeeBasicInfoJPAQuery
      .from(qEmployeeBasicInfo)
      .where(predicate)
      .limit(pageable.getPageSize())
      .offset(pageable.getOffset())
      .orderBy(qEmployeeBasicInfo.createdDate.desc());

    return new PageImpl<>(copyList(query.fetch(), EmployeeBasicInfoDto.class), pageable, query.fetchCount());
  }

}
