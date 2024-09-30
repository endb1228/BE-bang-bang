package com.bangbang.member.dto;

import com.bangbang.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {

    @JsonProperty("userId")
    private Long id;
    private String email;
    private String password;

    public static MemberResponse getMember(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }

    public static List<MemberResponse> getMemberList(List<Member> members) {
        return members.stream()
                .map(MemberResponse::getMember)
                .collect(Collectors.toList());
    }
}
