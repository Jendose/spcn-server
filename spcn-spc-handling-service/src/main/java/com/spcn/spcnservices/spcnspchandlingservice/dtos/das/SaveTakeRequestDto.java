package com.spcn.spcnservices.spcnspchandlingservice.dtos.das;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveTakeRequestDto {
    Long courseId;
    TakeDasDto take;

    public SaveTakeRequestDto() {
    }
}
