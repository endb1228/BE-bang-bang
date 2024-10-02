package com.bangbang.member.service;

import com.bangbang.member.domain.Member;
import com.bangbang.member.dto.MemberRequest;
import com.bangbang.member.exception.MemberException;
import com.bangbang.member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void signup(MemberRequest request) throws MemberException {
        if (memberRepository.findByAccount(request.getAccount()).isPresent()) {
            throw MemberException.MEMBER_ACCOUNT_DUPLICATED;
        }
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw MemberException.MEMBER_EMAIL_DUPLICATED;
        }
        if (memberRepository.findByNickname(request.getEmail()).isPresent()) {
            throw MemberException.MEMBER_NICKNAME_DUPLICATED;
        }
        memberRepository.save(Member.create(request.getAccount(), request.getEmail(), request.getNickname(), request.getPassword()));
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

    public Member getInfo(Long id) {
        return memberRepository.findById(id).get();
    }
}
