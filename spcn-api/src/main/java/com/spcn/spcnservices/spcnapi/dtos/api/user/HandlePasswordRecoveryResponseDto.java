package com.spcn.spcnservices.spcnapi.dtos.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandlePasswordRecoveryResponseDto {
    private String code;

    public HandlePasswordRecoveryResponseDto() {
    }
}
