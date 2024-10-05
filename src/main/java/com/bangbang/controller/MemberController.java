package com.bangbang.controller;

import static com.bangbang.controller.MemberController.MEMBER_API_BASE_URL;

import com.bangbang.domain.Course;
import com.bangbang.domain.Member;
import com.bangbang.domain.MemberCourse;
import com.bangbang.dto.request.LoginRequest;
import com.bangbang.dto.request.ModifyMemberInfoRequest;
import com.bangbang.dto.request.SignupRequest;
import com.bangbang.dto.response.CourseResponse;
import com.bangbang.dto.response.GetMemberCourseResponse;
import com.bangbang.dto.response.GetMemberInfoResponse;
import com.bangbang.exception.CourseException;
import com.bangbang.exception.MemberException;
import com.bangbang.service.CourseService;
import com.bangbang.service.MemberService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    private final CourseService courseService;
    private final MemberService memberService;

    public MemberController(CourseService courseService, MemberService memberService) {
        this.courseService = courseService;
        this.memberService = memberService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "회원가입 실패"),
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
        try {
            return ResponseEntity.ok(GetMemberInfoResponse.from(memberService.signup(request)));
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "로그인 실패")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(GetMemberInfoResponse.from(memberService.login(request)));
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
            } else if (!email.isEmpty()) {
                memberService.validateEmail(email);
            } else if (!nickname.isEmpty()) {
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
            return ResponseEntity.ok(GetMemberInfoResponse.from(member));
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
                                        @Valid @RequestBody ModifyMemberInfoRequest memberRequest) {
        try {
            return ResponseEntity.ok().body(GetMemberInfoResponse.from(memberService.modifyInfo(id, memberRequest)));
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "변경 성공"),
            @ApiResponse(responseCode = "400", description = "변경 실패")
    })
    @PatchMapping("/{userId}/course/{courseId}")
    public ResponseEntity<?> addCourse(@PathVariable Long userId,
                                       @PathVariable Long courseId) {
        try {
            memberService.addCourse(userId, courseId);
            return ResponseEntity.ok().build();
        } catch (MemberException | CourseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}/course")
    public ResponseEntity<?> getCourseList(@PathVariable Long userId,
                                           @RequestParam(name = "c") boolean isCompleted) {
        List<MemberCourse> memberCourseList = memberService.getCourseList(userId, isCompleted);
        return ResponseEntity.ok().body(
                memberCourseList.stream()
                        .map(m -> GetMemberCourseResponse.from(m.getCourse().getId(), m.stampNum())).toList()
        );
    }

    @GetMapping("/{userId}/courseList")
    public ResponseEntity<?> getCourseList(@PathVariable Long userId) {
        List<Course> courseList = courseService.getCourseList();
        List<CourseResponse> courseResponses = courseList.stream().map(c -> {
            List<String> stampTime = memberService.getStampTime(userId, c.getId());
            return CourseResponse.from(c, stampTime);
        }).toList();
        return ResponseEntity.ok().body(courseResponses);
    }

    @GetMapping("/{userId}/course/{courseId}/memo")
    public ResponseEntity<?> getMemo(@PathVariable Long userId,
                                     @PathVariable Long courseId) {
        try {
            return ResponseEntity.ok().body(memberService.getMemo(userId, courseId));
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{userId}/course/{courseId}/memo")
    public ResponseEntity<?> modifyMemo(@PathVariable Long userId,
                                    @PathVariable Long courseId,
                                    @RequestBody String memo) {
        try {
            return ResponseEntity.ok().body(memberService.modifyMemo(userId, courseId, memo));
        } catch (MemberException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{userId}/course/{courseId}/stamp/{heritageOrder}")
    public ResponseEntity<?> addStamp(@PathVariable Long userId,
                                      @PathVariable Long courseId,
                                      @PathVariable int heritageOrder) {
        memberService.addStamp(userId, courseId, heritageOrder);
        return ResponseEntity.ok().build();
    }
}
