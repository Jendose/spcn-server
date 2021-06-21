package com.spcn.spcnservices.spcndataaccessservice.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeDasDto {
    private Long id;
    private CourseDasDto course;
    private LocalTime takeTime;

    @Override
    public String toString(){
        return takeTime.toString();
    }

}
