package com.bangbang.repository;


import com.bangbang.domain.Course;
import com.bangbang.domain.MemberCourse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByMemberCourses(List<MemberCourse> memberCourses);
}
