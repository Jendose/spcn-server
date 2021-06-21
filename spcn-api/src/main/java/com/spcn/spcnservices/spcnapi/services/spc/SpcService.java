package com.spcn.spcnservices.spcnapi.services.spc;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpcService {

    /**
     * Запрос DAS-30 Отправка
     * */
    public Boolean testConnection(String spcIp){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(spcIp + "/connectionTest", Boolean.class);
        return response.getBody();
    }

}
