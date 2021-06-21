package com.spcn.spcnservices.spcnapi;

import com.spcn.spcnservices.spcnapi.dtos.das.UserDasDto;
import com.spcn.spcnservices.spcnapi.services.das.UserDasService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class DasServisesTests {

    private final UserDasService userDasService;

    @Autowired
    public DasServisesTests(UserDasService userDasService) {
        this.userDasService = userDasService;
    }

    @Test
    void getUserByEmail() {
        String email = "amacatoe@icloud.com";
        UserDasDto userDasDto = userDasService.getUserById(1L);
        System.out.println(userDasDto);

    }

}
