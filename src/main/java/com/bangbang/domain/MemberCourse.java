package com.bangbang.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private boolean completed;
    private String stamp;
    @Setter
    private String memo;

    public MemberCourse(Member member, Course course) {
        this.member = member;
        this.course = course;
        if(course.getCourseHeritages().isEmpty()) {
            this.stamp = " ";
        } else {
            this.stamp = " " + ", ".repeat(course.getCourseHeritages().size()-1);
        }
    }

    public int stampNum() {
        int i=0;
        String[] strings = stamp.split(",");
        for (String s : strings) {
            if (s != null && !s.isEmpty() && !s.equals(" ")) {
                i++;
            }
        }
        return i;
    }

    public List<String> stampList() {
        return Arrays.asList(stamp.split(","));
    }

    public void setStamp(int order) {
        List<String> stampList = stampList();
        stampList.set(order, LocalDateTime.now().toString());
        stamp = String.join(",", stampList);
    }
}
