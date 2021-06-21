package com.spcn.spcnservices.spcnapi.dtos.api.monitoring;

import lombok.Data;

@Data
public class HandleMonitoringNotificationResponseDto {
    String code;
    String addresseeEmail;
    String addresseeName;
    Long spcOwnerId;

}
