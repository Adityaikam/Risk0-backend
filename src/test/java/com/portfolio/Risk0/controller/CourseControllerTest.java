package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.Course;
import com.portfolio.Risk0.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private Course course1;
    private Course course2;

    @BeforeEach
    void setUp() {
        course1 = new Course();
        course1.setId(1L);
        course1.setTitle("Java Basics");
        course1.setInstructor("John Doe");
        course1.setCategory("Programming");
        course1.setPrice(new BigDecimal("99.99"));

        course2 = new Course();
        course2.setId(2L);
        course2.setTitle("Python Advanced");
        course2.setInstructor("Jane Smith");
        course2.setCategory("Programming");
    }

    @Test
    void testGetAllCourses() {
        when(courseService.getAllCourses()).thenReturn(Arrays.asList(course1, course2));

        List<Course> result = courseController.getAllCourses(null, null);

        assertEquals(2, result.size());
        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    void testGetAllCoursesByCategory() {
        when(courseService.getCoursesByCategory("Programming")).thenReturn(Arrays.asList(course1, course2));

        List<Course> result = courseController.getAllCourses("Programming", null);

        assertEquals(2, result.size());
        verify(courseService, times(1)).getCoursesByCategory("Programming");
        verify(courseService, never()).getAllCourses();
    }

    @Test
    void testGetAllCoursesByInstructor() {
        when(courseService.getCoursesByInstructor("John Doe")).thenReturn(List.of(course1));

        List<Course> result = courseController.getAllCourses(null, "John Doe");

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getInstructor());
        verify(courseService, times(1)).getCoursesByInstructor("John Doe");
    }

    @Test
    void testGetAllCoursesCategoryTakesPrecedence() {
        when(courseService.getCoursesByCategory("Programming")).thenReturn(Arrays.asList(course1, course2));

        List<Course> result = courseController.getAllCourses("Programming", "John Doe");

        assertEquals(2, result.size());
        verify(courseService, times(1)).getCoursesByCategory("Programming");
        verify(courseService, never()).getCoursesByInstructor(anyString());
    }

    @Test
    void testGetCourseById() {
        when(courseService.getCourseById(1L)).thenReturn(course1);

        Course result = courseController.getCourseById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Java Basics", result.getTitle());
        verify(courseService, times(1)).getCourseById(1L);
    }

    @Test
    void testGetCourseByIdNotFound() {
        when(courseService.getCourseById(999L)).thenReturn(null);

        Course result = courseController.getCourseById(999L);

        assertNull(result);
    }

    @Test
    void testAddCourse() {
        when(courseService.addCourse(any(Course.class))).thenReturn(course1);

        Course result = courseController.addCourse(course1);

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
        verify(courseService, times(1)).addCourse(course1);
    }

    @Test
    void testUpdateCourse() {
        Course updatedCourse = new Course();
        updatedCourse.setTitle("Java Advanced");

        when(courseService.updateCourse(eq(1L), any(Course.class))).thenReturn(updatedCourse);

        Course result = courseController.updateCourse(1L, updatedCourse);

        assertNotNull(result);
        assertEquals("Java Advanced", result.getTitle());
        verify(courseService, times(1)).updateCourse(1L, updatedCourse);
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseService).deleteCourse(1L);

        courseController.deleteCourse(1L);

        verify(courseService, times(1)).deleteCourse(1L);
    }
}
