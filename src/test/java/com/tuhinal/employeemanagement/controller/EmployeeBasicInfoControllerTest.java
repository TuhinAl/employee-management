package com.tuhinal.employeemanagement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuhinal.employeemanagement.dto.EmployeeBasicInfoDto;
import com.tuhinal.employeemanagement.service.EmployeeInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = EmployeeBasicInfoController.class,
excludeAutoConfiguration = {SecurityAutoConfiguration.class}
)// only this bean will be placed in ApplicationContext
//@AutoConfigureMockMvc(addFilters = false)
@MockBean({EmployeeInfoService.class})//ekhane implementation class gula hbe
public class EmployeeBasicInfoControllerTest {

    private final MockMvc mockMvc;

    private final EmployeeInfoService employeeInfoService; // and eta interface hbe,, ekhane 2ta way mone rakhte hbe(lecture-82, 2.30 minutes)

    public EmployeeBasicInfoControllerTest(MockMvc mockMvc, EmployeeInfoService employeeInfoService) {
        this.mockMvc = mockMvc;
        this.employeeInfoService = employeeInfoService;
    }

/*  @PostMapping("/save-info")
  public ResponseEntity<ApiResponse<EmployeeBasicInfoDto>> save(@RequestBody EmployeeBasicInfoDto employeeBasicInfoDto) {
    return responseFactory.saveResponse(employeeInfoService.save(employeeBasicInfoDto));
  }

  @PostMapping("/search")
  public ResponseEntity<ApiResponse<Page<EmployeeBasicInfoDto>>> search(@RequestBody EmployeeBasicInfoSearchDto
                                                                          employeeBasicInfoSearchDto) {
    return responseFactory.getResponse(employeeInfoService.search(employeeBasicInfoSearchDto));

  }*/

    @Test()
    @DisplayName("Employee Basic Info Save")
    void testSaveEmployeeBasicInfo() throws Exception {
        //Arrange
        //configure content of http body
            //create java object
        EmployeeBasicInfoDto employeeBasicInfoDto = new EmployeeBasicInfoDto();
        employeeBasicInfoDto.setFirstName("Tanvir");
        employeeBasicInfoDto.setLastName("Hasnan");
        employeeBasicInfoDto.setCurrentSalary(25000D);
        employeeBasicInfoDto.setDob(LocalDate.of(1996,10,10));

        //this data will return from service layer
        EmployeeBasicInfoDto employeeBasicInfoDtoFromService = new EmployeeBasicInfoDto();
        employeeBasicInfoDtoFromService.setFirstName("Tanvir");
        employeeBasicInfoDtoFromService.setLastName("Hasnan");
        employeeBasicInfoDtoFromService.setCurrentSalary(25000D);
//        employeeBasicInfoDtoFromService.setEmployeeNcId(UUID.randomUUID().toString());
        employeeBasicInfoDtoFromService.setDob(LocalDate.of(1996,10,10));

        when(employeeInfoService.save(any(EmployeeBasicInfoDto.class))).thenReturn(employeeBasicInfoDtoFromService);



             // convert java object to string

        //no need to send the request over the network
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save-info-duplicate")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(employeeBasicInfoDto));

        //Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();

        EmployeeBasicInfoDto createdEmployeeBasicInfoDto = new ObjectMapper().readValue(responseBodyAsString,
            EmployeeBasicInfoDto.class);

        //Assert

        Assertions.assertEquals(employeeBasicInfoDto.getFirstName(),
            createdEmployeeBasicInfoDto.getFirstName(),
            "The Returned user first name is most likely incorrect.");
    }

}