package com.spcn.spcnservices.spcnapi.utils.mappers;

import com.spcn.spcnservices.spcnapi.dtos.api.course.CourseApiDto;
import com.spcn.spcnservices.spcnapi.dtos.das.CourseDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.SpcDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.TimeDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.UserDasDto;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class CourseMapper {

    public static CourseApiDto mapCourseModelToDto(CourseDasDto course){
        CourseApiDto courseDto = new CourseApiDto();
        courseDto.setId(course.getId());
        courseDto.setMedicine(course.getMedicine());
        courseDto.setSpcSerialNumber(course.getSpc().getSerialNumber());
        courseDto.setDateStarted(course.getDateStarted().toString());
        courseDto.setDateFinished(course.getDateFinished().toString());
        courseDto.setTimetable(
                course.getTimetable()
                        .stream()
                        .map(TimeDasDto::toString)
                        .collect(toList())
        );
        courseDto.setTakeDurationSec(course.getTakeDurationSec());
        return courseDto;
    }

    public static CourseDasDto mapCourseDtoToModel(CourseApiDto courseDto, UserDasDto user, SpcDasDto spc){
        CourseDasDto course = new CourseDasDto();
        course.setMedicine(courseDto.getMedicine());
        course.setUser(user);
        course.setSpc(spc);
        course.setMedicine(courseDto.getMedicine());
        course.setTimetable(
                courseDto.getTimetable()
                        .stream()
                        .map(time -> new TimeDasDto(LocalTime.parse(time)))
                        .collect(toSet())
        );
        course.setDateStarted(LocalDate.parse(courseDto.getDateStarted()));
        course.setDateFinished(LocalDate.parse(courseDto.getDateFinished()));
        course.setTakeDurationSec(courseDto.getTakeDurationSec());
        return course;
    }

}
