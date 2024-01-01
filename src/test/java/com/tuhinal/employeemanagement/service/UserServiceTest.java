package com.tuhinal.employeemanagement.service;

import com.tuhinal.employeemanagement.service.tdd.model.User;
import com.tuhinal.employeemanagement.service.tdd.repository.UserRepository;
import com.tuhinal.employeemanagement.service.tdd.service.EmailVerificationServiceImpl;
import com.tuhinal.employeemanagement.service.tdd.service.UserServiceException;
import com.tuhinal.employeemanagement.service.tdd.service.UserServiceImpl;
import com.tuhinal.employeemanagement.service.tdd.service.exception.EmailNotificationServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@Order(3)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks //Mark a field on which injection should be performed.
    UserServiceImpl userService;
    @Mock //Mark a field as a mock.
    UserRepository userRepository;

    @Mock
    EmailVerificationServiceImpl emailVerificationService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void init() {
        firstName = "Alauddin";
        lastName = "Tuhin";
        email = "alauddintuhin1411@gmail.com";
        password = "12345678";
        repeatPassword = "12345678";
    }


//    @Test
//    void userLogin() {
//        System.out.println("Customer Logged in successfully");
//
//    }
//    @Test
//    void userLogout() {
//        System.out.println("Customer Logged out.");
//     }

    @DisplayName("User Object Created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        /** Arrange - In this phase, you set up the preconditions and the initial state for the test scenario
         This involves preparing the system
         and its environment to ensure that the conditions are suitable for testing. */
        //Arrange
        when(userRepository.save(any(User.class))).thenReturn(true);

        /**
         *Act:
         * This is the phase where you perform the actual actions or operations that you want to test.
         * It involves executing the specific behavior or functionality that you are
         * interested in evaluating.
         */
        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
        /** Assert
         * In the assert phase, you verify the outcome of the actions taken in the previous phase.
         * You check whether the system's behavior aligns with the expected results.
         * If there are any discrepancies, the test will typically fail, indicating a potential issue.
         */
        //Assert
        assertNotNull(user, "The create user should not return null.");
        assertEquals(firstName, user.getFirstName(), "User's firstName is incorrect");
        assertEquals(lastName, user.getLastName(), "User's lastName is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");
        assertNotNull(user.getId(), "User id is missing");
        verify(userRepository).save(any(User.class));
    }

    @DisplayName("Save Method thrown Exception")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
        when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);
        assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown UserServiceException instead");
    }


    //todo important
    @DisplayName("Email Notification Method thrown Exception")
    @Test
    void testCreateUser_whenEmailNotificationThrowsException_thenThrowsUserServiceException() {

        //Arrange
        //when().thenReturn() and when().thenThrow() is not working with void method;
        when(userRepository.save(any(User.class))).thenReturn(true);
        doThrow(EmailNotificationServiceException.class)
            .when(emailVerificationService)
            .scheduleEmailConfirmation(any(User.class));

        //todo doNothing().when()
        //Act
//        userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Act and Assert
        assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown UserServiceException instead");

        //Assert
        verify(emailVerificationService, times(1))
            .scheduleEmailConfirmation(any(User.class));
    }

    //Call a real method
    @DisplayName("Schedule Email Confirmation is Executed")
    @Test
    void testCreateUser_whenUserCreated_scheduleEmailConfirms() {
        when(userRepository.save(any(User.class))).thenReturn(true);
        doCallRealMethod()
            .when(emailVerificationService)
            .scheduleEmailConfirmation(any(User.class));

        //Act
        userService.createUser(firstName, lastName, email, password, repeatPassword);

        //assert
        verify(emailVerificationService, times(1))
            .scheduleEmailConfirmation(any(User.class));
    }

}
