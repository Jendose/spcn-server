package com.spcn.spcnservices.spcnspchandlingservice.dtos.das;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeDasDto {
    private Long id;
    private LocalDateTime date;
    private Boolean taken;
    private TakeStatus takeStatus;

    @Override
    public String toString() {
        return "TakeDasDto{" +
                "date=" + date +
                ", takeStatus=" + takeStatus +
                ", taken=" + taken +
                '}';
    }
}
