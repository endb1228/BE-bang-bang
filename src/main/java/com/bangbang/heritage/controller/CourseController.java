package com.bangbang.heritage.controller;

import com.bangbang.heritage.domain.Course;
import com.bangbang.heritage.dto.CourseResponse;
import com.bangbang.heritage.service.CourseService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiResponse(responseCode = "200", description = "코스 목록 조회 성공")
    @GetMapping
    public ResponseEntity<?> getCourseList() {
        List<Course> courseList = courseService.getCourseList();
        return ResponseEntity.ok().body(courseList.stream().map(CourseResponse::from).toList());
    }
}
