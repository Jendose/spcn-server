package com.spcn.spcnservices.spcndataaccessservice.utils.mappers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.MonitoringDasDto;
import com.spcn.spcnservices.spcndataaccessservice.dtos.UserDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Monitoring;
import com.spcn.spcnservices.spcndataaccessservice.model.User;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class UserMapper {

    private static UserDasDto mapUserModelToDto(User user, boolean spcOwnersSetNull){
        UserDasDto userDto = new UserDasDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setIsDependent(user.getIsDependent());
        if (spcOwnersSetNull) userDto.setMonitoringsByThisCaretaker(null);
        else {
            Set<MonitoringDasDto> monitoringsByThisCaretaker = new HashSet<>();
            for(Monitoring monitoring : user.getMonitoringsByThisCaretaker()){
                MonitoringDasDto monitoringDto = new MonitoringDasDto();
                monitoringDto.setId(monitoring.getId());
                monitoringDto.setSpcOwner(mapUserModelToDto(monitoring.getSpcOwner(), true));
                monitoringDto.setCaretaker(null);
                monitoringDto.setIsHost(monitoring.getIsHost());
                monitoringsByThisCaretaker.add(monitoringDto);
            }
            userDto.setMonitoringsByThisCaretaker(monitoringsByThisCaretaker);
        }
        // Т.к. нас ваще не интересует список опекунов данного юзера,
        // просто фигачим в нулл мониторинги, где данный юзер является опекаемым
        userDto.setMonitoringsOfThisSpcOwner(null);
        userDto.setCourses(
                user.getCourses()
                        .stream()
                        .map(CourseMapper::mapCourseModelToDto)
                        .collect(toSet())
        );
        userDto.setSpcSet(
                user.getSpcSet()
                        .stream()
                        .map(SpcMapper::mapSpcModelToDto)
                        .collect(toSet())
        );
        return userDto;
    }

    public static UserDasDto mapUserModelToDto(User user){
        if (user != null) {
            System.out.println(user.toString());
            UserDasDto userDto = mapUserModelToDto(user, false);
            System.out.println(userDto.toString());
            return userDto;
        }
        else return null;
    }

}
