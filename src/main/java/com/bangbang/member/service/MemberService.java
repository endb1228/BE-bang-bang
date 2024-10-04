package com.bangbang.member.service;

import com.bangbang.heritage.domain.Course;
import com.bangbang.heritage.exception.CourseException;
import com.bangbang.heritage.repository.CourseHeritageRepository;
import com.bangbang.heritage.repository.CourseRepository;
import com.bangbang.heritage.repository.StampRepository;
import com.bangbang.member.domain.Member;
import com.bangbang.member.domain.MemberCourse;
import com.bangbang.member.dto.MemberRequest;
import com.bangbang.member.exception.MemberException;
import com.bangbang.member.repository.MemberCourseRepository;
import com.bangbang.member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final MemberCourseRepository memberCourseRepository;
    private final CourseHeritageRepository courseHeritageRepository;
    private final StampRepository stampRepository;

    public MemberService(MemberRepository memberRepository, CourseRepository courseRepository,
                         MemberCourseRepository memberCourseRepository,
                         CourseHeritageRepository courseHeritageRepository, StampRepository stampRepository) {
        this.memberRepository = memberRepository;
        this.courseRepository = courseRepository;
        this.memberCourseRepository = memberCourseRepository;
        this.courseHeritageRepository = courseHeritageRepository;
        this.stampRepository = stampRepository;
    }

    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Member signup(MemberRequest request) throws MemberException {
        return memberRepository.save(
                Member.create(request.getAccount(), request.getEmail(), request.getNickname(), request.getPassword()));
    }

    public Member login(MemberRequest request) throws MemberException {
        Member member = memberRepository.findByAccount(request.getAccount())
                .orElseThrow(() -> MemberException.MEMBER_ACCOUNT_NOT_FOUND);
        validatePassword(member, request.getPassword());
        return member;
    }

    private void validatePassword(Member member, String password) throws MemberException {
        if (!member.getPassword().equals(password)) {
            throw MemberException.MEMBER_PASSWORD_NOT_CORRECT;
        }
    }

    public void validateAccount(String account) throws MemberException {
        if (memberRepository.findByAccount(account).isPresent()) {
            throw MemberException.MEMBER_ACCOUNT_DUPLICATED;
        }
    }

    public void validateEmail(String email) throws MemberException {
        if (memberRepository.findByAccount(email).isPresent()) {
            throw MemberException.MEMBER_EMAIL_DUPLICATED;
        }
    }

    public void validateNickname(String nickname) throws MemberException {
        if (memberRepository.findByAccount(nickname).isPresent()) {
            throw MemberException.MEMBER_NICKNAME_DUPLICATED;
        }
    }

    public Member getInfo(Long id) throws MemberException {
        return memberRepository.findById(id).orElseThrow(() -> MemberException.MEMBER_ID_NOT_FOUND);

    }

    public void modifyInfo(Long id, MemberRequest request) throws MemberException {
        Member member = getInfo(id);
        member.modify(request.getNickname(), request.getPassword());
    }


    public void addCourse(Long userId, Long courseId) throws MemberException, CourseException {
        Member member = memberRepository.findById(userId).orElseThrow(() -> MemberException.MEMBER_ID_NOT_FOUND);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> CourseException.COURSE_NOT_FOUND);
        if(memberCourseRepository.existsByMemberIdAndCourseId(userId, courseId)) {
            throw MemberException.MEMBER_ALREADY_HAS_COURSE;
        }
        MemberCourse memberCourse = new MemberCourse(member, course);
        memberCourseRepository.save(memberCourse);
    }

    public List<Course> getCourseList(Long userId) {
        return memberCourseRepository.findAllByMemberId(userId).stream()
                .map(MemberCourse::getCourse).toList();
    }
}
