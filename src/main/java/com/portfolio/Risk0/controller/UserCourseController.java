package com.portfolio.Risk0.controller;

import com.portfolio.Risk0.model.UserCourse;
import com.portfolio.Risk0.service.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserCourseController {

    private final UserCourseService userCourseService;

    @GetMapping
    public List<UserCourse> getAllUserCourses(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long courseId) {
        if (userId != null) {
            return userCourseService.getUserCoursesByUserId(userId);
        }
        if (courseId != null) {
            return userCourseService.getUserCoursesByCourseId(courseId);
        }
        return userCourseService.getAllUserCourses();
    }

    @GetMapping("/{id}")
    public UserCourse getUserCourseById(@PathVariable Long id) {
        return userCourseService.getUserCourseById(id);
    }

    @GetMapping("/enrollment")
    public UserCourse getUserCourseByUserIdAndCourseId(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        return userCourseService.getUserCourseByUserIdAndCourseId(userId, courseId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCourse enrollCourse(@RequestBody UserCourse userCourse) {
        return userCourseService.enrollCourse(userCourse);
    }

    @PutMapping("/{id}")
    public UserCourse updateUserCourse(@PathVariable Long id, @RequestBody UserCourse userCourse) {
        return userCourseService.updateUserCourse(id, userCourse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserCourse(@PathVariable Long id) {
        userCourseService.deleteUserCourse(id);
    }
}
