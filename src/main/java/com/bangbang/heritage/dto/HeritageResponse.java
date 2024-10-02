package com.bangbang.heritage.dto;

import com.bangbang.heritage.domain.Heritage;
import com.bangbang.heritage.domain.HeritageType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HeritageResponse {
    private Long id;
    private String name;
    private HeritageType type;
    private double utmkN;
    private double utmkE;

    public static HeritageResponse from(Heritage heritage) {
        return HeritageResponse.builder()
                .id(heritage.getId())
                .name(heritage.getName())
                .type(heritage.getType())
                .utmkN(heritage.getUtmkN())
                .utmkE(heritage.getUtmkE())
                .build();
    }
}
