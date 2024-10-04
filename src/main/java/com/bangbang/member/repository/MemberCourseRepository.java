package com.bangbang.member.repository;

import com.bangbang.member.domain.MemberCourse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCourseRepository extends JpaRepository<MemberCourse, Long> {

    List<MemberCourse> findAllByMemberId(Long userId);

    boolean existsByMemberIdAndCourseId(Long userId, Long courseId);

    Optional<MemberCourse> findByMemberIdAndCourseId(Long userId, Long courseId);
}
