package com.spcn.spcnservices.spcnapi.utils.mappers;

import com.spcn.spcnservices.spcnapi.dtos.api.user.UserApiDto;
import com.spcn.spcnservices.spcnapi.dtos.das.SpcDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.UserDasDto;

import static java.util.stream.Collectors.toList;

public class UserMapper {

    public static UserApiDto mapUserModelToDto(UserDasDto user){
        UserApiDto userDto = new UserApiDto();
        if (user != null) {
            userDto.setId(user.getId());
            userDto.setUsername(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setCourses(
                    user.getCourses() != null ?
                            user.getCourses()
                                    .stream()
                                    .map(CourseMapper::mapCourseModelToDto)
                                    .collect(toList())
                            : null
            );
            userDto.setSpcOwners(
                    user.getMonitoringsByThisCaretaker() != null ?
                            user.getMonitoringsByThisCaretaker()
                                    .stream()
                                    .map(monitoring -> mapUserModelToDto(monitoring.getSpcOwner()))
                                    .collect(toList())
                            : null
            );
            userDto.setSpcSerialNumberList(
                    user.getSpcSet() != null ?
                            user.getSpcSet()
                                    .stream()
                                    .map(SpcDasDto::getSerialNumber)
                                    .collect(toList())
                            : null
            );
            userDto.setHasCaretaker(false);
            userDto.setIsDependent(user.getIsDependent());
            return userDto;
        }
        else return null;
    }

}
