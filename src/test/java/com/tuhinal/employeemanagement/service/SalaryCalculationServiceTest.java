package com.tuhinal.employeemanagement.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Employee Salary Calculate")
class SalaryCalculationServiceTest {

    SalaryCalculationService service;

    @BeforeAll
    static void init() {
        System.out.println("@BeforeAll SalaryCalculationServiceTest initialized.");
    }

    @AfterAll
    static void destroy() {
        System.out.println(" @AfterAll SalaryCalculationServiceTest test class destroyed.");
    }


    @BeforeEach
    void objectCreation() {
        System.out.println("@BeforeEach => objet create()");
        service = new SalaryCalculationService();
    }


    @AfterEach
    void objectDestroy() {
        service = null;
        System.out.println("@AfterEach => objet destroyed()");
    }

    // namig convention
    // test<System Under Test>_<Condition or State Change>_<Expected Result>

    @Test
    @DisplayName("Test 4/2 = 2")
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {
        //code structure with help of AAA Arrange, Act, Assert
        // Arrange: prepare and initialize all the needed variables and objects
        // Act:
        // Assert
    }

    @Test
    @DisplayName("gross salary created.")
    void grossSalaryCalculate() {
        //code structure with help of AAA Arrange, Act, Assert
        // Arrange(Given): prepare and initialize all the needed variables and objects
//        SalaryCalculationService service = new SalaryCalculationService();
        Double bSalary = 20000.00;
        Double expectedResult = 20000.00;
        // Act(When): Invoke the method() yu are testing.
        Double grossSalaryCalculate = service.grossSalaryCalculate(bSalary);
        // Assert(Then): used to validate the return value
        assertEquals(expectedResult, grossSalaryCalculate, "20002.00 is not equal to 20000.00");
    }

    @Test
    @DisplayName("provident Fund Calculation ")
    void providentFundCalculation() {
        System.out.println("Your Provident fund matured");
    }

    @Disabled("Still needs to work on this test case.")
    @Test
    void testDemo() {
//        fail("No Implementation Provided");
    }

    /**
     * Junit Lifecycle
     * (1) @BeforeAll(should be static method)
     * (2) @BeforeEach = before any test operation, need to be clean state, i.e object create, database connection etc
     * (3) @Test
     * (4) @AfterEach (close DB connection, clean-up resources)
     * (5) @AfterAll(should be static method)
     */


    @Test
    @DisplayName("/ by zero.")
    void testIntegerDivisionByZero() {
        //code structure with help of AAA Arrange, Act, Assert
        // Arrange(Given): prepare and initialize all the needed variables and objects
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMethod = "Divided by zero";

        // Act(When): Invoke the method() yu are testing.
        // Act and assert
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> {
            //Act
             service.integerDivision(dividend, divisor);
        }, "Division by zero should through Exception.");
        // Assert(Then): used to validate the return value
        assertEquals(expectedExceptionMethod, arithmeticException.getMessage(), "Unexpected Exception message.");
    }


    // ================== Advanced JUnit ===========================

    // parameterized test
    @DisplayName("Test Integer Subtraction [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
//    @MethodSource("testIntegerSubtraction")
//    @CsvSource({"33, 2, 31","35, 2, 33","100, 5, 95"})
/*    @CsvSource({"apple, orange",
   " apple, ''", //
    "apple"}) // for null*/

//    @CsvFileSource(resources = "/PATH/TO/RESOURCE/FILE")
    @CsvFileSource(resources = "/integerSubtraction.csv")
    void testIntegerSubtraction(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test 33-1=32");
        Integer integerDivision = service.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, integerDivision, ()-> minuend +"-"+subtrahend +"did not produce " + expectedResult);
    }

    private static Stream<Arguments> testIntegerSubtraction() {
        return Stream.of(
                Arguments.of(33, 2, 31),
                Arguments.of(35, 2, 33),
                Arguments.of(100, 5, 95),
                Arguments.of(Integer.MIN_VALUE, 2, (Integer.MIN_VALUE - 2))
        );
    }


    // @ParametericedTest + @ValueSource

    @ParameterizedTest
    @ValueSource(strings = {"Tanvir", "Tuhin"})
    void valueSourceAnnotationDemonstration(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }

}