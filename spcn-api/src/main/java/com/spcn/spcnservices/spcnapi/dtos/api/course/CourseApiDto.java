package com.spcn.spcnservices.spcnapi.dtos.api.course;

import lombok.Data;

import java.util.List;

@Data
public class CourseApiDto {
    Long id;
    String medicine;
    String spcSerialNumber;
    String dateStarted;
    String dateFinished;
    List<String> timetable;
    Integer takeDurationSec;

    @Override
    public String toString() {
        return "CourseApiDto{" +
                "id=" + id +
                ", medicine='" + medicine + '\'' +
                ", spcSerialNumber='" + spcSerialNumber + '\'' +
                ", dateStarted='" + dateStarted + '\'' +
                ", dateFinished='" + dateFinished + '\'' +
                ", timetable=" + timetable +
                ", takeDurationSec=" + takeDurationSec +
                '}';
    }
}
