package com.spcn.spcnservices.spcnapi.dtos.das;

import com.spcn.spcnservices.spcnapi.dtos.enums.TakeStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeDasDto {
    private Long id;
    private CourseDasDto course;
    private LocalDateTime date;
    private TakeStatus takeStatus;
    private Boolean taken;

    @Override
    public String toString() {
        return "\nTakeDasDto{" +
                "date=" + date +
                ", takeStatus=" + takeStatus +
                ", taken=" + taken +
                '}';
    }
}
