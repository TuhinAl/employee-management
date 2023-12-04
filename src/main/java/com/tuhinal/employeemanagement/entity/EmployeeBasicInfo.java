package com.tuhinal.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tuhinal.employeemanagement.enums.DesignationTypeEnum;
import com.tuhinal.employeemanagement.enums.PaymentTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee_basic_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EmployeeBasicInfo extends Auditable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "employee_nc_id")
    private String employeeNcId;

    @Column(name = "designation_type_enum_key")
    @Enumerated(EnumType.STRING)
    private DesignationTypeEnum designationTypeEnumKey;

    @Column(name = "designation_type_enum_value")
    private String designationTypeEnumValue;

    @Column(name = "phone")
    private String phone;

    @Column(name = "present_address")
    private String presentAddress;

    @Column(name = "permanent_address")
    private String permanentAddress;

    @Column(name = "dob")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    
    @Column(name = "payment_type_enum_key")
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentTypeEnumKey;
    
    @Column(name = "payment_type_enum_value")
    private String paymentTypeEnumValue;

    @Column(name = "current_salary")
    private Double currentSalary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_account_id")
    private EmployeeAccount employeeAccount;

    @OneToMany(mappedBy = "employeeBasicInfo", fetch = FetchType.LAZY)
    private List<EmployeeAccountTransaction> employeeAccountTransactionList;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_attendance_id")
    private EmployeeAttendance employeeAttendance;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_bank_info_id")
    private EmployeeBankInfo employeeBankInfo;
    
   public EmployeeBasicInfo(String id) {
        this.id = id;
    }
}
