package com.spcn.spcnservices.spcnapi.dtos.api.monitoring;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveMonitoringRequestDto {
    Long caretakerId;
    Long spcOwnerId;

    public SaveMonitoringRequestDto() {
    }
}
