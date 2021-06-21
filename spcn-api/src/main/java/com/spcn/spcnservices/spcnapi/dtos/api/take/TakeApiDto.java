package com.spcn.spcnservices.spcnapi.dtos.api.take;

import com.spcn.spcnservices.spcnapi.dtos.enums.TakeStatus;
import lombok.Data;

@Data
public class TakeApiDto {
    String date;
    TakeStatus status;
    Boolean taken;

    public TakeApiDto(String date, TakeStatus status, Boolean taken) {
        this.date = date;
        this.status = status;
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "\nTakeApiDto{" +
                "date='" + date + '\'' +
                ", status=" + status.name() +
                ", taken=" + taken +
                "}";
    }
}
