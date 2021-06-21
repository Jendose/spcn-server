package com.spcn.spcnservices.spcndataaccessservice.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class SpcDasDto {
    private Long id;
    private String serialNumber;
    private String ip;
    private UserDasDto user;
    private Set<CourseDasDto> courses;

    @Override
    public String toString() {
        return serialNumber;
    }

}
