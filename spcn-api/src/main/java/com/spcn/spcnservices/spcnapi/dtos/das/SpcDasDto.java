package com.spcn.spcnservices.spcnapi.dtos.das;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SpcDasDto {
    private Long id;
    private String serialNumber;
    private String ip;
    private UserDasDto user;
    private Set<CourseDasDto> courses;

    public SpcDasDto() {
    }

    public SpcDasDto(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return serialNumber;
    }
}
