package com.bangbang.heritage.repository;

import com.bangbang.heritage.domain.CourseHeritage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseHeritageRepository extends JpaRepository<CourseHeritage, Long> {

}
