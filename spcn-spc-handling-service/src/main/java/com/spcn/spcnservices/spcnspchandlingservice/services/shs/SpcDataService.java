package com.spcn.spcnservices.spcnspchandlingservice.services.shs;

import org.springframework.stereotype.Service;

// Этот сервис не нужен, если дозатор присылает данные сразу в ответе
@Service
public class SpcDataService {

    public void saveData(Integer gercon, String spcIp){
        boolean taken = gercon > 0;
    }

}
