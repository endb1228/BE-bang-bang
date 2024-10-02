package com.bangbang.member.controller;

import static com.bangbang.member.controller.MemberController.MEMBER_API_BASE_URL;

import com.bangbang.member.domain.Member;
import com.bangbang.member.dto.MemberRequest;
import com.bangbang.member.dto.MemberResponse;
import com.bangbang.member.exception.MemberException;
import com.bangbang.member.service.MemberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
        origins = {"http://localhost:3000"},
        methods = {RequestMethod.GET, RequestMethod.POST},
        maxAge = 3600L
)
@RestController
@RequestMapping(MEMBER_API_BASE_URL)
public class MemberController {
    public static final String MEMBER_API_BASE_URL = "/user";

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "회원가입 실패"),
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberRequest request) {
        try {
            memberService.signup(request);
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "로그인 실패")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequest request) {
        try {
            Member member = memberService.login(request);
            return ResponseEntity.ok(MemberResponse.from(member));
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> info(@PathVariable Long id) {
        Member member = memberService.getInfo(id);
        return ResponseEntity.ok(MemberResponse.from(member));
    }
}
