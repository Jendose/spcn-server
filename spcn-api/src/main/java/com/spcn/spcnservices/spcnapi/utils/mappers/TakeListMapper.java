package com.spcn.spcnservices.spcnapi.utils.mappers;

import com.spcn.spcnservices.spcnapi.dtos.api.take.TakeApiDto;
import com.spcn.spcnservices.spcnapi.dtos.das.TakeDasDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TakeListMapper {

    public static List<TakeApiDto> mapTakeModelToDto(List<TakeDasDto> takes){
        return takes
                .stream()
                .map(take -> new TakeApiDto(
                        take.getDate().toString(),
                        take.getTakeStatus(),
                        take.getTaken()
                ))
                .collect(toList());
    }

}
