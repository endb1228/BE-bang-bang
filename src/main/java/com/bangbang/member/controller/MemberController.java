package com.bangbang.member.controller;

import static com.bangbang.member.controller.MemberController.MEMBER_API_BASE_URL;

import com.bangbang.member.domain.Member;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MEMBER_API_BASE_URL)
public class MemberController {
    public static final String MEMBER_API_BASE_URL = "/test";

//    @GetMapping("/userList")
//    public List<Member> getMemberList() {
//
//    }
}
