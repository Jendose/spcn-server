package com.spcn.spcnservices.spcndataaccessservice.utils.mappers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.TimeDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Time;

public class TimeMapper {
    public static TimeDasDto mapTimeModelToDto(Time time){
        TimeDasDto timeDto = new TimeDasDto();
        timeDto.setId(time.getId());
        timeDto.setCourse(null);
        timeDto.setTakeTime(time.getTakeTime());
        return timeDto;
    }
}
