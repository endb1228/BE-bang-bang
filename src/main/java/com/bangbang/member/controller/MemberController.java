package com.bangbang.member.controller;

import static com.bangbang.member.controller.MemberController.MEMBER_API_BASE_URL;

import com.bangbang.member.dto.MemberRequest;
import com.bangbang.member.dto.MemberResponse;
import com.bangbang.member.service.MemberService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MEMBER_API_BASE_URL)
public class MemberController {
    public static final String MEMBER_API_BASE_URL = "/test";

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/userList")
    public List<MemberResponse> getMemberList() {
        return MemberResponse.getMemberList(memberService.getMemberList());
    }

    @GetMapping("/user/{userId}")
    public MemberResponse getMember(@PathVariable Long userId) {
        return MemberResponse.getMember(memberService.getMember(userId));
    }

    @PostMapping("/user")
    public MemberResponse createMember(@RequestBody MemberRequest request) {
        return MemberResponse.getMember(memberService.createMember(request));
    }
}
