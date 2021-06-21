package com.spcn.spcnservices.spcnapi.dtos.api.user;

import lombok.Data;

@Data
public class AuthUserRequestDto {
    String email;
    String password;

}
