package com.bangbang.member.service;

import com.bangbang.member.domain.Member;
import com.bangbang.member.dto.MemberRequest;
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

    public void signup(MemberRequest request) {
        if(memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException();
        }
        memberRepository.save(Member.create(request.getEmail(), request.getPassword()));
    }

    public void login(MemberRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(IllegalArgumentException::new);
        validatePassword(member, request.getPassword());
    }

    private void validatePassword(Member member, String password) {
        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException();
        }
    }
}
