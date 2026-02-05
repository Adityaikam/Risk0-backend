package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.Course;
import com.portfolio.Risk0.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

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
        course2.setPrice(new BigDecimal("149.99"));
    }

    @Test
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        List<Course> result = courseService.getAllCourses();

        assertEquals(2, result.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testGetAllCoursesEmpty() {
        when(courseRepository.findAll()).thenReturn(List.of());

        List<Course> result = courseService.getAllCourses();

        assertTrue(result.isEmpty());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testGetCoursesByCategory() {
        when(courseRepository.findByCategory("Programming")).thenReturn(Arrays.asList(course1, course2));

        List<Course> result = courseService.getCoursesByCategory("Programming");

        assertEquals(2, result.size());
        verify(courseRepository, times(1)).findByCategory("Programming");
    }

    @Test
    void testGetCoursesByCategoryEmpty() {
        when(courseRepository.findByCategory("NonExistent")).thenReturn(List.of());

        List<Course> result = courseService.getCoursesByCategory("NonExistent");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCoursesByInstructor() {
        when(courseRepository.findByInstructor("John Doe")).thenReturn(List.of(course1));

        List<Course> result = courseService.getCoursesByInstructor("John Doe");

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getInstructor());
        verify(courseRepository, times(1)).findByInstructor("John Doe");
    }

    @Test
    void testGetCourseById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));

        Course result = courseService.getCourseById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Java Basics", result.getTitle());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCourseByIdNotFound() {
        when(courseRepository.findById(999L)).thenReturn(Optional.empty());

        Course result = courseService.getCourseById(999L);

        assertNull(result);
        verify(courseRepository, times(1)).findById(999L);
    }

    @Test
    void testAddCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(course1);

        Course result = courseService.addCourse(course1);

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
        verify(courseRepository, times(1)).save(course1);
    }

    @Test
    void testUpdateCourse() {
        Course updatedCourse = new Course();
        updatedCourse.setTitle("Java Advanced");

        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> {
            Course saved = invocation.getArgument(0);
            return saved;
        });

        Course result = courseService.updateCourse(1L, updatedCourse);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Java Advanced", result.getTitle());
        verify(courseRepository, times(1)).save(updatedCourse);
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseRepository).deleteById(1L);

        courseService.deleteCourse(1L);

        verify(courseRepository, times(1)).deleteById(1L);
    }
}
