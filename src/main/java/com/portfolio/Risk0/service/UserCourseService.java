package com.portfolio.Risk0.service;

import com.portfolio.Risk0.model.UserCourse;
import com.portfolio.Risk0.repository.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCourseService {

    private final UserCourseRepository userCourseRepository;

    public List<UserCourse> getAllUserCourses() {
        return userCourseRepository.findAll();
    }

    public List<UserCourse> getUserCoursesByUserId(Long userId) {
        return userCourseRepository.findByUserId(userId);
    }

    public List<UserCourse> getUserCoursesByCourseId(Long courseId) {
        return userCourseRepository.findByCourseId(courseId);
    }

    public UserCourse getUserCourseById(Long id) {
        return userCourseRepository.findById(id).orElse(null);
    }

    public UserCourse getUserCourseByUserIdAndCourseId(Long userId, Long courseId) {
        return userCourseRepository.findByUserIdAndCourseId(userId, courseId).orElse(null);
    }

    public UserCourse enrollCourse(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    public UserCourse updateUserCourse(Long id, UserCourse userCourse) {
        userCourse.setId(id);
        return userCourseRepository.save(userCourse);
    }

    public void deleteUserCourse(Long id) {
        userCourseRepository.deleteById(id);
    }
}
