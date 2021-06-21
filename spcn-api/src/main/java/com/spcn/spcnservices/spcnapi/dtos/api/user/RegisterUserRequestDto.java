package com.spcn.spcnservices.spcnapi.dtos.api.user;

import lombok.Data;

@Data
public class RegisterUserRequestDto {
    String username;
    String email;
    String password;
    Boolean isDependent;
    
}
