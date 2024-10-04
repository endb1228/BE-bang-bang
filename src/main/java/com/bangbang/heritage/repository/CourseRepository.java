package com.bangbang.heritage.repository;


import com.bangbang.heritage.domain.Course;
import com.bangbang.heritage.domain.Heritage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
