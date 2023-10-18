package com.tuhinal.employeemanagement.tdd;

import com.tuhinal.employeemanagement.service.tdd.User;
import com.tuhinal.employeemanagement.service.tdd.service.UserService;
import com.tuhinal.employeemanagement.service.tdd.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService;
    String id;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;
    ;

    void init() {
        //Arrange
        userService = new UserServiceImpl();
        id = "ID-100";
        firstName = "Alauddin";
        lastName = "Tuhin";
        email = "test@tes.com";
        password = "12345678";
        repeatPassword = "12345678";
    }

    @Test
    @DisplayName("user object created")
    void testCreateUser_whenUserDetailsProvided_returnUserObject() {

        //Act
        User user = userService.createUser(id, firstName, lastName, email, password, repeatPassword);
        //Assert
        assertNotNull(user, "The createUser() method shouldn't have return null");
        assertNotNull(user.getId(), "user ID is missing");
        assertEquals(firstName, user.getFirstName(), "User firstName is incorrect");
        assertEquals(lastName, user.getLastName(), "User lastName is incorrect");
        assertEquals(email, user.getEmail(), "User email is incorrect");

    }

    @Test
    @DisplayName("firstName validation")
    void testCreateUser_whenFirstNameIsEmpty_ThrowIllegalException() {
        //Arrange
        UserService userService = new UserServiceImpl();
        String id = "";
        String firstName = "";
        String lastName = "Tuhin";
        String email = "test@tes.com";
        String password = "12345678";
        String repeatPassword = "12345678";


        String expectedException = "User's firstName is missing";
        //Act
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(id, firstName, lastName, email, password, repeatPassword);
        }, "Empty firstName should have cause an Illegal Argument Exception");
        //Assert
        assertEquals(expectedException, illegalArgumentException.getMessage(), "Exception Error message is not correct.");

    }
/*
    @Test
    void testCreateUser_whenUserCreated_returnUserObjectContainsSameFirstName() {
        //Arrange
        UserService userService = new UserServiceImpl();
        String firstName = "Alauddin";
        String lastName = "Tuhin";
        String email = "test@tes.com";
        String password = "12345678";
        String repeatPassword = "12345678";
        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
        //Assert
        assertEquals(firstName, user.getFirstName() );

    }*/

}
