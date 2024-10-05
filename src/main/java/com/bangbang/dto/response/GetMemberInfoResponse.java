package com.bangbang.dto.response;

import com.bangbang.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMemberInfoResponse {

    @Schema(description = "유저 PK", example = "1")
    private Long id;
    @Schema(description = "아이디", example = "account1234")
    private String account;
    @Schema(description = "이메일", example = "email1234@gmail.com")
    private String email;
    @Schema(description = "닉네임", example = "nickname")
    private String nickname;
    @Schema(description = "프로필 사진 url", example = "https://image-server.com/1")
    private String profileImageUrl;

    public static GetMemberInfoResponse from(Member member) {
        return GetMemberInfoResponse.builder()
                .id(member.getId())
                .account(member.getAccount())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }
}
