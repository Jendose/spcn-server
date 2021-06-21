package com.spcn.spcnservices.spcnapi.dtos.das;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeDasDto {
    private Long id;
    private CourseDasDto course;
    private LocalTime takeTime;

    public TimeDasDto() {
    }

    public TimeDasDto(LocalTime takeTime) {
        this.takeTime = takeTime;
    }

    @Override
    public String toString(){
        return takeTime.toString();
    }
}
