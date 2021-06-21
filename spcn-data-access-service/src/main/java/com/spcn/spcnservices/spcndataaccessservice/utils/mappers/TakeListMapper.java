package com.spcn.spcnservices.spcndataaccessservice.utils.mappers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.TakeDasDto;
import com.spcn.spcnservices.spcndataaccessservice.dtos.TakeListDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Take;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TakeListMapper {
    public static TakeListDasDto mapTakeListModelToDto(List<Take> takeList){
        if (takeList != null) {
            System.out.println(takeList.toString());
            List<TakeDasDto> takeDtoList = takeList
                    .stream()
                    .map(TakeMapper::mapTakeModelToDto)
                    .collect(toList());
            System.out.println(takeDtoList.toString());
            return new TakeListDasDto(takeDtoList);
        }
        else return null;
    }
}
