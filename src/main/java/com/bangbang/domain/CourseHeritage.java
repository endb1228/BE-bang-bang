package com.bangbang.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseHeritage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "heritage_id")
    private Heritage heritage;
    private Long heritageOrder;

    public CourseHeritage(Course course, Heritage heritage, Long heritageOrder) {
        this.course = course;
        this.heritage = heritage;
        this.heritageOrder = heritageOrder;
        course.getCourseHeritages().add(this);
    }
}
