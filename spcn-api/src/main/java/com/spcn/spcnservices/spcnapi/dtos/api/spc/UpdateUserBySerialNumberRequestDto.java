package com.spcn.spcnservices.spcnapi.dtos.api.spc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserBySerialNumberRequestDto {
    private Long userId;

    public UpdateUserBySerialNumberRequestDto() {
    }
}
