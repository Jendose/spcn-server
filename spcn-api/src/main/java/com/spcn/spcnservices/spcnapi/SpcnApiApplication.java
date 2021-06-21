package com.spcn.spcnservices.spcnapi;

import com.spcn.spcnservices.spcnapi.dtos.api.user.RegisterUserRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.api.user.RegisterUserResponseDto;
import com.spcn.spcnservices.spcnapi.services.api.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpcnApiApplication /*implements CommandLineRunner*/ {

    private final UserApiService userApiService;

    @Autowired
    public SpcnApiApplication(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpcnApiApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        RegisterUserRequestDto user1 = new RegisterUserRequestDto();
//        user1.setUsername("Евгений Шацков");
//        user1.setEmail("zhenya.shatskov@yandex.ru");
//        user1.setPassword("123456");
//        user1.setIsDependent(false);
//        RegisterUserResponseDto registerUser1Response = userApiService.registerUser(user1);
//    }
}
