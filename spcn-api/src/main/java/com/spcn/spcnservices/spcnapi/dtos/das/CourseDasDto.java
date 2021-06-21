package com.spcn.spcnservices.spcnapi.dtos.das;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CourseDasDto {
    private Long id;
    private UserDasDto user;
    private SpcDasDto spc;
    private String medicine;
    private Set<TimeDasDto> timetable;
    private Set<TakeDasDto> takeSet;
    private LocalDate dateStarted;
    private LocalDate dateFinished;
    private Integer takeDurationSec;

    @Override
    public String toString() {
        return "CourseDasDto{" +
                "id=" + id +
                ", spc=" + spc.toString() +
                ", medicine='" + medicine + '\'' +
                ", timetable=" + timetable.toString() +
                ", takeSet=" + takeSet.toString() +
                ", dateStarted=" + dateStarted +
                ", dateFinished=" + dateFinished +
                ", takeDurationSec=" + takeDurationSec +
                '}';
    }
}
