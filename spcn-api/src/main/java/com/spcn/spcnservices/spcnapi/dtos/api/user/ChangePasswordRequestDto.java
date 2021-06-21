package com.spcn.spcnservices.spcnapi.dtos.api.user;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    String email;
    String password;
    
}
