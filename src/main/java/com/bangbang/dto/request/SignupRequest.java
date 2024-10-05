package com.bangbang.dto.request;

import com.bangbang.util.constant.Regex;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {
    @Schema(description = "유저 아이디", example = "account1234", requiredMode = RequiredMode.REQUIRED)
    @Pattern(regexp= Regex.ACCOUNT)
    public String account;

    @Schema(description = "이메일", example = "email1234@gmail.com", requiredMode = RequiredMode.REQUIRED)
    @Email
    public String email;

    @Schema(description = "닉네임", example = "nickname", requiredMode = RequiredMode.REQUIRED)
    @Pattern(regexp=Regex.NICKNAME)
    public String nickname;

    @Schema(description = "비밀번호", example = "#qwer1234", requiredMode = RequiredMode.REQUIRED)
    @Pattern(regexp=Regex.PASSWORD)
    public String password;
}
