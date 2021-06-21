package com.spcn.spcnservices.spcndataaccessservice.dtos;

import com.spcn.spcnservices.spcndataaccessservice.model.enums.TakeStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeDasDto {
    private Long id;
    private CourseDasDto course;
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
