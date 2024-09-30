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

    public Member createMember(MemberRequest request) {
        return memberRepository.save(Member.create(request.getEmail(), request.getPassword()));
    }
}
