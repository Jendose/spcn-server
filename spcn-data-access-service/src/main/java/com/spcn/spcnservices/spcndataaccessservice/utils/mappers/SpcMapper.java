package com.spcn.spcnservices.spcndataaccessservice.utils.mappers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.SpcDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Spc;

public class SpcMapper {

    public static SpcDasDto mapSpcModelToDto(Spc spc){
        if (spc != null) {
            SpcDasDto spcDto = new SpcDasDto();
            spcDto.setId(spc.getId());
            spcDto.setSerialNumber(spc.getSerialNumber());
            spcDto.setIp(spc.getIp());
            spcDto.setUser(null);
            spcDto.setCourses(null);
            return spcDto;
        }
        else return null;
    }

}
