package com.spcn.spcnservices.spcnapi.dtos.api.course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveCourseRequestDto {
    CourseApiDto course;
    Long userId;

    public SaveCourseRequestDto() {
    }
}
