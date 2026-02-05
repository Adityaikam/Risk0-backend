package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

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
    void testGetUser() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        Optional<User> result = userService.getUser();

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserEmpty() {
        when(userRepository.findAll()).thenReturn(List.of());

        Optional<User> result = userService.getUser();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetUserMultipleReturnsFirst() {
        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("second@example.com");

        when(userRepository.findAll()).thenReturn(List.of(user, user2));

        Optional<User> result = userService.getUser();

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testUpdateUserExisting() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setEmail("old@example.com");

        User updateData = new User();
        updateData.setEmail("new@example.com");
        updateData.setPassword("newpass");
        updateData.setFirstName("Jane");
        updateData.setLastName("Smith");

        when(userRepository.findAll()).thenReturn(List.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = userService.updateUser(updateData);

        assertNotNull(result);
        assertEquals("new@example.com", result.getEmail());
        assertEquals("newpass", result.getPassword());
        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testUpdateUserNoExisting() {
        User updateData = new User();
        updateData.setEmail("new@example.com");
        updateData.setPassword("newpass");
        updateData.setFirstName("Jane");
        updateData.setLastName("Smith");

        when(userRepository.findAll()).thenReturn(List.of());
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = userService.updateUser(updateData);

        assertNotNull(result);
        assertEquals("new@example.com", result.getEmail());
        assertEquals("Jane", result.getFirstName());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
