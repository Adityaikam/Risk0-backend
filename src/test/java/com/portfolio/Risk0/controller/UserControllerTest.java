package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setFirstName("John");
        user.setLastName("Doe");
    }

    @Test
    void testGetUserSuccess() {
        when(userService.getUser()).thenReturn(Optional.of(user));

        ResponseEntity<User> result = userController.getUser();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("test@example.com", result.getBody().getEmail());
        assertEquals("John", result.getBody().getFirstName());
        verify(userService, times(1)).getUser();
    }

    @Test
    void testGetUserNotFound() {
        when(userService.getUser()).thenReturn(Optional.empty());

        ResponseEntity<User> result = userController.getUser();

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(userService, times(1)).getUser();
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setEmail("updated@example.com");
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Smith");

        when(userService.updateUser(any(User.class))).thenReturn(updatedUser);

        ResponseEntity<User> result = userController.updateUser(updatedUser);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("updated@example.com", result.getBody().getEmail());
        assertEquals("Jane", result.getBody().getFirstName());
        verify(userService, times(1)).updateUser(updatedUser);
    }

    @Test
    void testUpdateUserNewUser() {
        User newUser = new User();
        newUser.setEmail("new@example.com");
        newUser.setPassword("newpass");
        newUser.setFirstName("New");
        newUser.setLastName("User");

        when(userService.updateUser(any(User.class))).thenReturn(newUser);

        ResponseEntity<User> result = userController.updateUser(newUser);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("new@example.com", result.getBody().getEmail());
        verify(userService, times(1)).updateUser(newUser);
    }
}
