package com.bangbang.member.domain;

import com.bangbang.heritage.domain.Course;
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
public class MemberCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    private String memo;

    public MemberCourse(Member member, Course course) {
        this.member = member;
        this.course = course;
    }
}
