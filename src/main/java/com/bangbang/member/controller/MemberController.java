package com.bangbang.member.controller;

import static com.bangbang.member.controller.MemberController.MEMBER_API_BASE_URL;

import com.bangbang.member.domain.Member;
import com.bangbang.member.dto.MemberRequest;
import com.bangbang.member.dto.MemberResponse;
import com.bangbang.member.exception.MemberException;
import com.bangbang.member.service.MemberService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping

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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유효한 입력"),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 입력")
    })
    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam(required = false) String account,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) String nickname) {
        try {
            if (!account.isEmpty()) {
                memberService.validateAccount(account);
            } else if (email.isEmpty()) {
                memberService.validateEmail(email);
            } else if (nickname.isEmpty()) {
                memberService.validateNickname(nickname);
            } else {
                return ResponseEntity.badRequest().body("파라미터 입력이 잘못되었습니다.");
            }
            return ResponseEntity.ok().build();
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "조회 실패")
    })
    @GetMapping("/info/{id}")
    public ResponseEntity<?> getInfo(@PathVariable Long id) {
        try {
            Member member = memberService.getInfo(id);
            return ResponseEntity.ok(MemberResponse.from(member));
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "변경 성공"),
            @ApiResponse(responseCode = "400", description = "변경 실패")
    })
    @PutMapping("/info/{id}")
    public ResponseEntity<?> modifyInfo(@PathVariable Long id,
                                        @RequestBody MemberRequest memberRequest) {
        try {
            memberService.modifyInfo(id, memberRequest);
            return ResponseEntity.ok().build();
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
