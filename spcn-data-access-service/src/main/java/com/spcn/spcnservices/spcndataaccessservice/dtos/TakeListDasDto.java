package com.spcn.spcnservices.spcndataaccessservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TakeListDasDto {
    private List<TakeDasDto> takeList;

    public TakeListDasDto() {
    }
}
