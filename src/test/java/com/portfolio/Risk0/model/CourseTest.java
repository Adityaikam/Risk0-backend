package com.portfolio.Risk0.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testNoArgsConstructor() {
        Course course = new Course();
        assertNotNull(course);
        assertNull(course.getId());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Course course = new Course(1L, "Java Basics", "John Doe", "Learn Java",
                "Programming", "10 hours", new BigDecimal("99.99"),
                new BigDecimal("4.50"), 100, "http://image.url", now);

        assertEquals(1L, course.getId());
        assertEquals("Java Basics", course.getTitle());
        assertEquals("John Doe", course.getInstructor());
        assertEquals("Learn Java", course.getDescription());
        assertEquals("Programming", course.getCategory());
        assertEquals("10 hours", course.getDuration());
        assertEquals(new BigDecimal("99.99"), course.getPrice());
        assertEquals(new BigDecimal("4.50"), course.getRating());
        assertEquals(100, course.getStudentsCount());
        assertEquals("http://image.url", course.getImageUrl());
        assertEquals(now, course.getCreatedAt());
    }

    @Test
    void testSettersAndGetters() {
        Course course = new Course();

        course.setId(1L);
        course.setTitle("Python Basics");
        course.setInstructor("Jane Doe");
        course.setDescription("Learn Python");
        course.setCategory("Programming");
        course.setDuration("8 hours");
        course.setPrice(new BigDecimal("79.99"));
        course.setRating(new BigDecimal("4.80"));
        course.setStudentsCount(200);
        course.setImageUrl("http://python.url");

        assertEquals(1L, course.getId());
        assertEquals("Python Basics", course.getTitle());
        assertEquals("Jane Doe", course.getInstructor());
        assertEquals("Learn Python", course.getDescription());
        assertEquals("Programming", course.getCategory());
        assertEquals("8 hours", course.getDuration());
        assertEquals(new BigDecimal("79.99"), course.getPrice());
        assertEquals(new BigDecimal("4.80"), course.getRating());
        assertEquals(200, course.getStudentsCount());
        assertEquals("http://python.url", course.getImageUrl());
    }

    @Test
    void testEqualsAndHashCode() {
        Course course1 = new Course();
        course1.setId(1L);
        course1.setTitle("Java Basics");

        Course course2 = new Course();
        course2.setId(1L);
        course2.setTitle("Java Basics");

        assertEquals(course1, course2);
        assertEquals(course1.hashCode(), course2.hashCode());
    }

    @Test
    void testNotEquals() {
        Course course1 = new Course();
        course1.setId(1L);

        Course course2 = new Course();
        course2.setId(2L);

        assertNotEquals(course1, course2);
    }

    @Test
    void testToString() {
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");

        String toString = course.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Course"));
    }

    @Test
    void testDefaultStudentsCount() {
        Course course = new Course();
        assertEquals(0, course.getStudentsCount());
    }
}
