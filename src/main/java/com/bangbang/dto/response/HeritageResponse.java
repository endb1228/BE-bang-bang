package com.bangbang.dto.response;

import com.bangbang.domain.Heritage;
import com.bangbang.domain.HeritageType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HeritageResponse {
    private Long id;
    private String name;
    private String time;
//    private HeritageType type;
    private double utmkN;
    private double utmkE;

    public static HeritageResponse from(Heritage heritage, String time) {
        return HeritageResponse.builder()
                .id(heritage.getId())
                .name(heritage.getName())
                .time(time)
                .utmkN(heritage.getUtmkN())
                .utmkE(heritage.getUtmkE())
                .build();
    }
}
