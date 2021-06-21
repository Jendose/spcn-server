package com.spcn.spcnservices.spcndataaccessservice.dtos;

import lombok.Data;

@Data
public class MonitoringDasDto {
    private Long id;
    private UserDasDto spcOwner;
    private UserDasDto caretaker;
    private Boolean isHost;

    @Override
    public String toString() {
        return "MonitoringDasDto{" +
                "spcOwner=" + spcOwner.getId() +
                ", isHost=" + isHost +
                '}';
    }

}
