package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testNoArgsConstructor() {
        User user = new User();
        assertNotNull(user);
        assertNull(user.getId());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1L, "test@example.com", "password123",
                "John", "Doe", "ABCDE1234F", "1234567890", now, now);

        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("ABCDE1234F", user.getPanNumber());
        assertEquals("1234567890", user.getPhoneNumber());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    void testSettersAndGetters() {
        User user = new User();

        user.setId(1L);
        user.setEmail("user@example.com");
        user.setPassword("securePass");
        user.setFirstName("Alice");
        user.setLastName("Smith");
        user.setPanNumber("XYZAB9876P");
        user.setPhoneNumber("9876543210");

        assertEquals(1L, user.getId());
        assertEquals("user@example.com", user.getEmail());
        assertEquals("securePass", user.getPassword());
        assertEquals("Alice", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("XYZAB9876P", user.getPanNumber());
        assertEquals("9876543210", user.getPhoneNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("test@example.com");

        User user2 = new User();
        user2.setId(1L);
        user2.setEmail("test@example.com");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testNotEquals() {
        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        assertNotEquals(user1, user2);
    }

    @Test
    void testToString() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        String toString = user.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("User"));
    }
}
