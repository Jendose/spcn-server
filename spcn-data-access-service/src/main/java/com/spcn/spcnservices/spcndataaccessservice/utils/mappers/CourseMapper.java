package com.spcn.spcnservices.spcndataaccessservice.utils.mappers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.CourseDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Course;

import static java.util.stream.Collectors.toSet;

public class CourseMapper {

    public static CourseDasDto mapCourseModelToDto(Course course){
        CourseDasDto courseDto = new CourseDasDto();
        courseDto.setId(course.getId());
        courseDto.setUser(null);
        courseDto.setSpc(SpcMapper.mapSpcModelToDto(course.getSpc()));
        courseDto.setMedicine(course.getMedicine());
        courseDto.setTimetable(
                course.getTimetable()
                        .stream()
                        .map(TimeMapper::mapTimeModelToDto)
                        .collect(toSet())
        );
        courseDto.setTakeSet(
                course.getTakeSet()
                        .stream()
                        .map(TakeMapper::mapTakeModelToDto)
                        .collect(toSet())
        );
        courseDto.setDateStarted(course.getDateStarted());
        courseDto.setDateFinished(course.getDateFinished());
        courseDto.setTakeDurationSec(course.getTakeDurationSec());
        return courseDto;
    }

}
