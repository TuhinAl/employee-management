package com.tuhinal.employeemanagement.service;

import lombok.Data;

import java.util.Objects;

public class SalaryCalculationService {

    public Double grossSalaryCalculate(Double basicSalary) {
        Double gross = 0.0;
        ;
        if (Objects.nonNull(basicSalary)) {
            Double houseRent = (basicSalary * (0.30));
            Double taDa = (basicSalary * (0.10));
            gross = basicSalary + houseRent + taDa;
        }
        return basicSalary;
    }

    public Double providentFundCalculation(Double basicSalary, Emp emp) {
        int monthsInInteger = Integer.parseInt(emp.getMonths());
        monthsInInteger = (monthsInInteger - emp.getMonthsOfProbation());
        Double providentFund = Double.valueOf(0.0);
        Double contributoryFund = (basicSalary * (0.085) * monthsInInteger);

        if (monthsInInteger >= 24) {
            providentFund = (contributoryFund * 2);
        } else {
            providentFund = contributoryFund;
        }
        return providentFund;
    }


    void testDemo() {
        System.out.println("Demo test");
    }

    Integer integerDivision(int dividend, int divisor) {
        return dividend / divisor;
    }

    Integer integerSubtraction(int dividend, int divisor) {
        return dividend - divisor;
    }

}

@Data
class Emp {
    private String id;
    private String name;
    private String months;
    private Integer monthsOfProbation;
}
