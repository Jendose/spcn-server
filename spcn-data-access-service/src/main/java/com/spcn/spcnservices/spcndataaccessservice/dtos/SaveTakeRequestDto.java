package com.spcn.spcnservices.spcndataaccessservice.dtos;

import lombok.Data;

@Data
public class SaveTakeRequestDto {
    Long courseId;
    TakeDasDto take;
}
