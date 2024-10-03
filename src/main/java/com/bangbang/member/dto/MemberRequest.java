package com.bangbang.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberRequest {
    @Schema(description = "유저 아이디", example = "endb1228", requiredMode = RequiredMode.REQUIRED)
    @Pattern(regexp="[a-z1-9]{6,15}")
    public String account;
    @Schema(description = "이메일, 로그인 시에는 불필요", example = "endb1228@gmail.com", requiredMode = RequiredMode.NOT_REQUIRED)
    @Email
    public String email;
    @Schema(description = "닉네임, 로그인 시에는 불필요", example = "nickname", requiredMode = RequiredMode.NOT_REQUIRED)
    @Pattern(regexp="[a-zA-Z1-9가-힣]{4,10}")
    public String nickname;
    @Schema(description = "비밀번호", example = "123456", requiredMode = RequiredMode.REQUIRED)
    @Pattern(regexp="[a-zA-Z\\d`~!@#$%^&*()-_=+]{8,}")
    public String password;
}
