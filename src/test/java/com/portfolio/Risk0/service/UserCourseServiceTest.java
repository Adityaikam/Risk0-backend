package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.Course;
import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.model.UserCourse;
import com.portfolio.Risk0.repository.UserCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCourseServiceTest {

    @Mock
    private UserCourseRepository userCourseRepository;

    @InjectMocks
    private UserCourseService userCourseService;

    private UserCourse userCourse1;
    private UserCourse userCourse2;
    private User user;
    private Course course;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        course = new Course();
        course.setId(1L);
        course.setTitle("Java Basics");

        userCourse1 = new UserCourse();
        userCourse1.setId(1L);
        userCourse1.setUser(user);
        userCourse1.setCourse(course);
        userCourse1.setProgress(50);
        userCourse1.setCompletedLessons(5);
        userCourse1.setTotalLessons(10);

        userCourse2 = new UserCourse();
        userCourse2.setId(2L);
        userCourse2.setUser(user);
        userCourse2.setCourse(course);
        userCourse2.setProgress(100);
        userCourse2.setCompletedLessons(10);
        userCourse2.setTotalLessons(10);
    }

    @Test
    void testGetAllUserCourses() {
        when(userCourseRepository.findAll()).thenReturn(Arrays.asList(userCourse1, userCourse2));

        List<UserCourse> result = userCourseService.getAllUserCourses();

        assertEquals(2, result.size());
        verify(userCourseRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUserCoursesEmpty() {
        when(userCourseRepository.findAll()).thenReturn(List.of());

        List<UserCourse> result = userCourseService.getAllUserCourses();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetUserCoursesByUserId() {
        when(userCourseRepository.findByUserId(1L)).thenReturn(Arrays.asList(userCourse1, userCourse2));

        List<UserCourse> result = userCourseService.getUserCoursesByUserId(1L);

        assertEquals(2, result.size());
        verify(userCourseRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetUserCoursesByUserIdEmpty() {
        when(userCourseRepository.findByUserId(999L)).thenReturn(List.of());

        List<UserCourse> result = userCourseService.getUserCoursesByUserId(999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetUserCoursesByCourseId() {
        when(userCourseRepository.findByCourseId(1L)).thenReturn(Arrays.asList(userCourse1, userCourse2));

        List<UserCourse> result = userCourseService.getUserCoursesByCourseId(1L);

        assertEquals(2, result.size());
        verify(userCourseRepository, times(1)).findByCourseId(1L);
    }

    @Test
    void testGetUserCourseById() {
        when(userCourseRepository.findById(1L)).thenReturn(Optional.of(userCourse1));

        UserCourse result = userCourseService.getUserCourseById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(50, result.getProgress());
        verify(userCourseRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserCourseByIdNotFound() {
        when(userCourseRepository.findById(999L)).thenReturn(Optional.empty());

        UserCourse result = userCourseService.getUserCourseById(999L);

        assertNull(result);
    }

    @Test
    void testGetUserCourseByUserIdAndCourseId() {
        when(userCourseRepository.findByUserIdAndCourseId(1L, 1L)).thenReturn(Optional.of(userCourse1));

        UserCourse result = userCourseService.getUserCourseByUserIdAndCourseId(1L, 1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userCourseRepository, times(1)).findByUserIdAndCourseId(1L, 1L);
    }

    @Test
    void testGetUserCourseByUserIdAndCourseIdNotFound() {
        when(userCourseRepository.findByUserIdAndCourseId(999L, 999L)).thenReturn(Optional.empty());

        UserCourse result = userCourseService.getUserCourseByUserIdAndCourseId(999L, 999L);

        assertNull(result);
    }

    @Test
    void testEnrollCourse() {
        when(userCourseRepository.save(any(UserCourse.class))).thenReturn(userCourse1);

        UserCourse result = userCourseService.enrollCourse(userCourse1);

        assertNotNull(result);
        assertEquals(50, result.getProgress());
        verify(userCourseRepository, times(1)).save(userCourse1);
    }

    @Test
    void testUpdateUserCourse() {
        UserCourse updatedCourse = new UserCourse();
        updatedCourse.setProgress(75);
        updatedCourse.setCompletedLessons(7);

        when(userCourseRepository.save(any(UserCourse.class))).thenAnswer(inv -> inv.getArgument(0));

        UserCourse result = userCourseService.updateUserCourse(1L, updatedCourse);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(75, result.getProgress());
        verify(userCourseRepository, times(1)).save(updatedCourse);
    }

    @Test
    void testDeleteUserCourse() {
        doNothing().when(userCourseRepository).deleteById(1L);

        userCourseService.deleteUserCourse(1L);

        verify(userCourseRepository, times(1)).deleteById(1L);
    }
}
