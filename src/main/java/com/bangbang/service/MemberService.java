package com.bangbang.service;

import com.bangbang.domain.Course;
import com.bangbang.domain.CourseHeritage;
import com.bangbang.domain.Heritage;
import com.bangbang.domain.Member;
import com.bangbang.domain.MemberCourse;
import com.bangbang.dto.request.LoginRequest;
import com.bangbang.dto.request.ModifyMemberInfoRequest;
import com.bangbang.dto.request.SignupRequest;
import com.bangbang.exception.CourseException;
import com.bangbang.exception.MemberException;
import com.bangbang.repository.CourseHeritageRepository;
import com.bangbang.repository.CourseRepository;
import com.bangbang.repository.MemberCourseRepository;
import com.bangbang.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final MemberCourseRepository memberCourseRepository;
    private final CourseHeritageRepository courseHeritageRepository;

    public MemberService(MemberRepository memberRepository, CourseRepository courseRepository,
                         MemberCourseRepository memberCourseRepository,
                         CourseHeritageRepository courseHeritageRepository) {
        this.memberRepository = memberRepository;
        this.courseRepository = courseRepository;
        this.memberCourseRepository = memberCourseRepository;
        this.courseHeritageRepository = courseHeritageRepository;
    }

    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Member signup(SignupRequest request) throws MemberException {
        return memberRepository.save(
                Member.create(request.getAccount(), request.getEmail(), request.getNickname(), request.getPassword()));
    }

    public Member login(LoginRequest request) throws MemberException {
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
        if (memberRepository.findByEmail(email).isPresent()) {
            throw MemberException.MEMBER_EMAIL_DUPLICATED;
        }
    }

    public void validateNickname(String nickname) throws MemberException {
        if (memberRepository.findByNickname(nickname).isPresent()) {
            throw MemberException.MEMBER_NICKNAME_DUPLICATED;
        }
    }

    public Member getInfo(Long id) throws MemberException {
        return memberRepository.findById(id).orElseThrow(() -> MemberException.MEMBER_ID_NOT_FOUND);

    }

    public Member modifyInfo(Long id, ModifyMemberInfoRequest request) throws MemberException {
        Member member = getInfo(id);
        member.modify(request.getNickname(), request.getPassword());
        return memberRepository.save(member);
    }


    public void addCourse(Long userId, Long courseId) throws MemberException, CourseException {
        Member member = memberRepository.findById(userId).orElseThrow(() -> MemberException.MEMBER_ID_NOT_FOUND);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> CourseException.COURSE_NOT_FOUND);
        if (memberCourseRepository.existsByMemberIdAndCourseId(userId, courseId)) {
            throw MemberException.MEMBER_ALREADY_HAS_COURSE;
        }
        MemberCourse memberCourse = new MemberCourse(member, course);
        memberCourseRepository.save(memberCourse);
    }

    public List<Heritage> getHeritages(Long courseId) {
        return courseHeritageRepository.findAllByCourseIdOrderByHeritageOrder(courseId).stream()
                .map(CourseHeritage::getHeritage).toList();
    }

    public List<String> getStampTime(Long userId, Long courseId) {
        Optional<MemberCourse> memberCourse = memberCourseRepository.findByMemberIdAndCourseId(userId,
                courseId);
        if(memberCourse.isEmpty()) {
            int size = courseRepository.findById(courseId).get().getCourseHeritages().size();
            ArrayList<String> times = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                times.add("");
            }
            return times;
        }
        return memberCourse.get().stampList();
    }

    public String getMemo(Long userId, Long courseId) throws MemberException {
        MemberCourse memberCourse = memberCourseRepository.findByMemberIdAndCourseId(userId, courseId)
                .orElseThrow(() -> MemberException.MEMBER_NOT_HAS_COURSE);
        return memberCourse.getMemo();
    }

    public String modifyMemo(Long userId, Long courseId, String memo) throws MemberException {
        MemberCourse memberCourse = memberCourseRepository.findByMemberIdAndCourseId(userId, courseId)
                .orElseThrow(() -> MemberException.MEMBER_NOT_HAS_COURSE);
        memberCourse.setMemo(memo);
        return memberCourseRepository.save(memberCourse).getMemo();
    }

    public List<MemberCourse> getCourseList(Long memberId, boolean isCompleted) {
        return memberCourseRepository.findAllByMemberIdAndCompleted(memberId, isCompleted);
    }

    public List<MemberCourse> getCourseList(Long memberId) {
        return memberCourseRepository.findAllByMemberId(memberId);
    }

    public void addStamp(Long userId, Long courseId, int heritageOrder) {
        MemberCourse memberCourse = memberCourseRepository.findByMemberIdAndCourseId(userId, courseId).get();
        memberCourse.setStamp(heritageOrder);
        memberCourseRepository.save(memberCourse);
    }

    public int getStampNum(Long userId) {
        List<MemberCourse> memberCourses = memberCourseRepository.findAllByMemberId(userId);
        int i=0;
        for (MemberCourse memberCourse : memberCourses) {
            i+=memberCourse.stampNum();
        }
        return i;
    }
}
