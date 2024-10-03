package com.bangbang.heritage.dto;

import com.bangbang.heritage.domain.Course;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CourseResponse {

    private Long id;
    private String name;
    private List<String> heritages;

    public static CourseResponse from(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .heritages(course.getCourseHeritages().stream()
                        .map(e -> e.getHeritage().getName())
                        .collect(Collectors.toList()))
                .build();
    }
}
