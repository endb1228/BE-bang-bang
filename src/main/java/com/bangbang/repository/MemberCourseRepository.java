package com.bangbang.repository;

import com.bangbang.domain.Member;
import com.bangbang.domain.MemberCourse;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCourseRepository extends JpaRepository<MemberCourse, Long> {

    List<MemberCourse> findAllByMemberId(Long userId);
    List<MemberCourse> findAllByMember(Member member);

    boolean existsByMemberIdAndCourseId(Long userId, Long courseId);

    Optional<MemberCourse> findByMemberIdAndCourseId(Long userId, Long courseId);

    List<MemberCourse> findAllByMemberIdAndCompleted(Long userId, boolean completed);
}
