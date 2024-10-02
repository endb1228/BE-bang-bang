package com.bangbang.member.dto;

import com.bangbang.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {

    @JsonProperty("userId")
    private Long id;
    private String account;
    private String email;
    private String nickname;
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
