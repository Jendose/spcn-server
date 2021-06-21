package com.spcn.spcnservices.spcnapi.dtos.api.user;

import com.spcn.spcnservices.spcnapi.dtos.api.course.CourseApiDto;
import lombok.Data;

import java.util.List;

@Data
public class UserApiDto {
    Long id;
    String username;
    String email;
    List<CourseApiDto> courses;
    List<UserApiDto> spcOwners;
    List<String> spcSerialNumberList;
    Boolean hasCaretaker;
    Boolean isDependent;

    @Override
    public String toString() {
        return "\nUserApiDto{" +
                "\nid=" + id +
                "\nusername='" + username + '\'' +
                "\nemail='" + email + '\'' +
                "\ncourses=" + courses +
                "\nspcOwners=" + spcOwners +
                "\nspcSerialNumberList=" + spcSerialNumberList +
                "\nhasCaretaker=" + hasCaretaker +
                "\nisDependent=" + isDependent +
                "\n}";
    }
}
