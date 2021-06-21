package com.spcn.spcnservices.spcndataaccessservice.utils.mappers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.TakeDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Take;

public class TakeMapper {
    public static TakeDasDto mapTakeModelToDto(Take take){
        TakeDasDto takeDto = new TakeDasDto();
        takeDto.setId(take.getId());
        takeDto.setCourse(null);
        takeDto.setDate(take.getDate());
        takeDto.setTaken(take.getTaken());
        takeDto.setTakeStatus(take.getTakeStatus());
        return takeDto;
    }

    public static Take mapTakeDtoToModel(TakeDasDto takeDto){
        Take take = new Take();
        take.setId(takeDto.getId());
        take.setCourse(null);
        take.setDate(takeDto.getDate());
        take.setTaken(takeDto.getTaken());
        take.setTakeStatus(takeDto.getTakeStatus());
        return take;
    }
}
