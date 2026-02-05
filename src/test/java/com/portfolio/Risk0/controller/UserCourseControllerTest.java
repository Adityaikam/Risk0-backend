package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.Course;
import com.portfolio.Risk0.model.User;
import com.portfolio.Risk0.model.UserCourse;
import com.portfolio.Risk0.service.UserCourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCourseControllerTest {

    @Mock
    private UserCourseService userCourseService;

    @InjectMocks
    private UserCourseController userCourseController;

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
    }

    @Test
    void testGetAllUserCourses() {
        when(userCourseService.getAllUserCourses()).thenReturn(Arrays.asList(userCourse1, userCourse2));

        List<UserCourse> result = userCourseController.getAllUserCourses(null, null);

        assertEquals(2, result.size());
        verify(userCourseService, times(1)).getAllUserCourses();
    }

    @Test
    void testGetAllUserCoursesByUserId() {
        when(userCourseService.getUserCoursesByUserId(1L)).thenReturn(Arrays.asList(userCourse1, userCourse2));

        List<UserCourse> result = userCourseController.getAllUserCourses(1L, null);

        assertEquals(2, result.size());
        verify(userCourseService, times(1)).getUserCoursesByUserId(1L);
        verify(userCourseService, never()).getAllUserCourses();
    }

    @Test
    void testGetAllUserCoursesByCourseId() {
        when(userCourseService.getUserCoursesByCourseId(1L)).thenReturn(Arrays.asList(userCourse1, userCourse2));

        List<UserCourse> result = userCourseController.getAllUserCourses(null, 1L);

        assertEquals(2, result.size());
        verify(userCourseService, times(1)).getUserCoursesByCourseId(1L);
        verify(userCourseService, never()).getAllUserCourses();
    }

    @Test
    void testGetAllUserCoursesUserIdTakesPrecedence() {
        when(userCourseService.getUserCoursesByUserId(1L)).thenReturn(List.of(userCourse1));

        List<UserCourse> result = userCourseController.getAllUserCourses(1L, 1L);

        assertEquals(1, result.size());
        verify(userCourseService, times(1)).getUserCoursesByUserId(1L);
        verify(userCourseService, never()).getUserCoursesByCourseId(anyLong());
    }

    @Test
    void testGetUserCourseById() {
        when(userCourseService.getUserCourseById(1L)).thenReturn(userCourse1);

        UserCourse result = userCourseController.getUserCourseById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(50, result.getProgress());
        verify(userCourseService, times(1)).getUserCourseById(1L);
    }

    @Test
    void testGetUserCourseByIdNotFound() {
        when(userCourseService.getUserCourseById(999L)).thenReturn(null);

        UserCourse result = userCourseController.getUserCourseById(999L);

        assertNull(result);
    }

    @Test
    void testGetUserCourseByUserIdAndCourseId() {
        when(userCourseService.getUserCourseByUserIdAndCourseId(1L, 1L)).thenReturn(userCourse1);

        UserCourse result = userCourseController.getUserCourseByUserIdAndCourseId(1L, 1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userCourseService, times(1)).getUserCourseByUserIdAndCourseId(1L, 1L);
    }

    @Test
    void testGetUserCourseByUserIdAndCourseIdNotFound() {
        when(userCourseService.getUserCourseByUserIdAndCourseId(999L, 999L)).thenReturn(null);

        UserCourse result = userCourseController.getUserCourseByUserIdAndCourseId(999L, 999L);

        assertNull(result);
    }

    @Test
    void testEnrollCourse() {
        when(userCourseService.enrollCourse(any(UserCourse.class))).thenReturn(userCourse1);

        UserCourse result = userCourseController.enrollCourse(userCourse1);

        assertNotNull(result);
        assertEquals(50, result.getProgress());
        verify(userCourseService, times(1)).enrollCourse(userCourse1);
    }

    @Test
    void testUpdateUserCourse() {
        UserCourse updatedCourse = new UserCourse();
        updatedCourse.setProgress(75);

        when(userCourseService.updateUserCourse(eq(1L), any(UserCourse.class))).thenReturn(updatedCourse);

        UserCourse result = userCourseController.updateUserCourse(1L, updatedCourse);

        assertNotNull(result);
        assertEquals(75, result.getProgress());
        verify(userCourseService, times(1)).updateUserCourse(1L, updatedCourse);
    }

    @Test
    void testDeleteUserCourse() {
        doNothing().when(userCourseService).deleteUserCourse(1L);

        userCourseController.deleteUserCourse(1L);

        verify(userCourseService, times(1)).deleteUserCourse(1L);
    }
}
