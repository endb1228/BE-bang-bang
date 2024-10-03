package com.bangbang.heritage.controller;

import com.bangbang.heritage.domain.Course;
import com.bangbang.heritage.dto.CourseResponse;
import com.bangbang.heritage.service.CourseService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<?> getCourseList() {
        List<Course> courseList = courseService.getCourseList();
        return ResponseEntity.ok().body(courseList.stream().map(CourseResponse::from));
    }
}
