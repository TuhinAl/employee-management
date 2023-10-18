package com.tuhinal.employeemanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoRepeatedTest {
    SalaryCalculationService service;
    @BeforeEach
    void objectCreation() {
        System.out.println("@BeforeEach => objet create()");
        service = new SalaryCalculationService();
    }

    @RepeatedTest(value = 3)
    @DisplayName("Division by zero.")
    void testIntegerDivisionByZero(RepetitionInfo repetitionInfo, TestInfo testInfo) {
        System.out.println("Running "+testInfo.getTestMethod().get().getName());
        System.out.println("Repetition $ " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> {
            service.integerDivision(dividend, divisor);
        }, "Division by zero should through Exception.");

        assertEquals(expectedExceptionMessage, arithmeticException.getMessage(), "Unexpected Exception message.");
    }


}
