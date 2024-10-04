package com.bangbang.heritage.repository;

import com.bangbang.heritage.domain.Course;
import com.bangbang.heritage.domain.CourseHeritage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseHeritageRepository extends JpaRepository<CourseHeritage, Long> {

    List<CourseHeritage> findAllByCourseIdOrderByHeritageOrder(Long courseId);
}
