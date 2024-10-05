
package com.bangbang.dto.response;

import com.bangbang.domain.Course;
import com.bangbang.domain.CourseHeritage;
import com.bangbang.domain.Heritage;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMemberCourseResponse {

    private Long id;
    private String name;
    private int stampNum;
    private char completed;
    private String photoUrl;
    private List<HeritageResponse> heritageResponses;

    public static GetMemberCourseResponse from(Course course, List<String> time, int stampNum, boolean isCompleted) {
        List<Heritage> heritages = course.getCourseHeritages().stream().map(CourseHeritage::getHeritage).toList();
        ArrayList<HeritageResponse> heritageResponses = new ArrayList<>();
        int i=0;
        for (Heritage heritage : heritages) {
            heritageResponses.add(HeritageResponse.from(heritage, time.get(i++)));
        }

        return GetMemberCourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .stampNum(stampNum)
                .photoUrl(course.getPhotoUrl())
                .completed(isCompleted?'y':'n')
                .heritageResponses(heritageResponses)
                .build();
    }
}
