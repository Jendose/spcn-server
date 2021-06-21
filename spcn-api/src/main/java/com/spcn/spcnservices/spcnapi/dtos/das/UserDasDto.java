package com.spcn.spcnservices.spcnapi.dtos.das;

import lombok.Data;

import java.util.Set;

@Data
public class UserDasDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean isDependent;
    private Set<MonitoringDasDto> monitoringsByThisCaretaker;
    private Set<MonitoringDasDto> monitoringsOfThisSpcOwner;
    private Set<CourseDasDto> courses;
    private Set<SpcDasDto> spcSet;

    public UserDasDto() {
    }

    public UserDasDto(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nUserDasDto{" +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\nemail='" + email + '\'' +
                "\npassword='" + password + '\'' +
                "\nisDependent=" + isDependent +
                (monitoringsByThisCaretaker != null ? "\nmonitoringsByThisCaretaker=" + monitoringsByThisCaretaker.toString() : "\nmonitoringsByThisCaretaker=null") +
                (monitoringsOfThisSpcOwner != null ? "\nmonitoringsOfThisSpcOwner=" + monitoringsOfThisSpcOwner.toString() : "\nmonitoringsOfThisSpcOwner=null") +
                (courses != null ? "\ncourses=" + courses.toString() : "\ncourses=null") +
                (spcSet != null ? "\nspcSet=" + spcSet.toString() : "\nspcSet=null") +
                "\n}";
    }
}
