package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserCourseTest {

    @Test
    void testNoArgsConstructor() {
        UserCourse userCourse = new UserCourse();
        assertNotNull(userCourse);
        assertNull(userCourse.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User();
        user.setId(1L);
        Course course = new Course();
        course.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        UserCourse userCourse = new UserCourse(1L, user, course, 50, 5, 10, now, now);

        assertEquals(1L, userCourse.getId());
        assertEquals(user, userCourse.getUser());
        assertEquals(course, userCourse.getCourse());
        assertEquals(50, userCourse.getProgress());
        assertEquals(5, userCourse.getCompletedLessons());
        assertEquals(10, userCourse.getTotalLessons());
        assertEquals(now, userCourse.getEnrolledAt());
        assertEquals(now, userCourse.getLastAccessed());
    }

    @Test
    void testSettersAndGetters() {
        UserCourse userCourse = new UserCourse();
        User user = new User();
        user.setId(2L);
        Course course = new Course();
        course.setId(2L);
        LocalDateTime enrolledAt = LocalDateTime.of(2025, 10, 1, 9, 0);
        LocalDateTime lastAccessed = LocalDateTime.of(2025, 11, 15, 14, 30);

        userCourse.setId(1L);
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setProgress(75);
        userCourse.setCompletedLessons(15);
        userCourse.setTotalLessons(20);
        userCourse.setEnrolledAt(enrolledAt);
        userCourse.setLastAccessed(lastAccessed);

        assertEquals(1L, userCourse.getId());
        assertEquals(user, userCourse.getUser());
        assertEquals(course, userCourse.getCourse());
        assertEquals(75, userCourse.getProgress());
        assertEquals(15, userCourse.getCompletedLessons());
        assertEquals(20, userCourse.getTotalLessons());
        assertEquals(enrolledAt, userCourse.getEnrolledAt());
        assertEquals(lastAccessed, userCourse.getLastAccessed());
    }

    @Test
    void testDefaultValues() {
        UserCourse userCourse = new UserCourse();
        assertEquals(0, userCourse.getProgress());
        assertEquals(0, userCourse.getCompletedLessons());
        assertEquals(0, userCourse.getTotalLessons());
    }

    @Test
    void testEqualsAndHashCode() {
        UserCourse uc1 = new UserCourse();
        uc1.setId(1L);
        uc1.setProgress(50);

        UserCourse uc2 = new UserCourse();
        uc2.setId(1L);
        uc2.setProgress(50);

        assertEquals(uc1, uc2);
        assertEquals(uc1.hashCode(), uc2.hashCode());
    }

    @Test
    void testNotEquals() {
        UserCourse uc1 = new UserCourse();
        uc1.setId(1L);

        UserCourse uc2 = new UserCourse();
        uc2.setId(2L);

        assertNotEquals(uc1, uc2);
    }

    @Test
    void testToString() {
        UserCourse userCourse = new UserCourse();
        userCourse.setId(1L);
        userCourse.setProgress(25);

        String toString = userCourse.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("UserCourse"));
    }
}
