
package com.bangbang.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMemberCourseResponse {

    private Long id;
    private int stampNum;

    public static GetMemberCourseResponse from(Long id, int stampNum) {
        return GetMemberCourseResponse.builder()
                .id(id)
                .stampNum(stampNum)
                .build();
    }
}
