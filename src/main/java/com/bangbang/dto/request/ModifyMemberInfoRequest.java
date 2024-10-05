package com.bangbang.dto.request;

import com.bangbang.util.constant.Regex;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ModifyMemberInfoRequest {

    @Schema(description = "닉네임", example = "nickname", requiredMode = RequiredMode.NOT_REQUIRED)
    @Pattern(regexp= Regex.NICKNAME)
    public String nickname;

    @Schema(description = "비밀번호", example = "#qwer1234", requiredMode = RequiredMode.REQUIRED)
    @Pattern(regexp= Regex.PASSWORD)
    public String password;
}
