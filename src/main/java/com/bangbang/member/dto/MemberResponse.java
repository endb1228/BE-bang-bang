package com.bangbang.member.dto;

import com.bangbang.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {

    @Schema(description = "유저 PK", example = "1")
    private Long id;
    @Schema(description = "아이디", example = "endb1228")
    private String account;
    @Schema(description = "이메일", example = "endb1228@gmail.com")
    private String email;
    @Schema(description = "닉네임", example = "nickname")
    private String nickname;
    @Schema(description = "프로필 사진 url", example = "https://image-server.com/1")
    private String profileImageUrl;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .account(member.getAccount())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }
}
