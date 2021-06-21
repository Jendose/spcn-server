package com.spcn.spcnservices.spcnspchandlingservice.services.das;

import com.spcn.spcnservices.spcnspchandlingservice.dtos.das.SaveTakeRequestDto;
import com.spcn.spcnservices.spcnspchandlingservice.dtos.das.TakeStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DasService {

    private final String dasIp = "http://localhost:8080";

    /**
     * Запрос DAS-20! Добавление записи в таблицу take
     * */
    public Long saveTake(SaveTakeRequestDto data){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> response = restTemplate.postForEntity(dasIp + "/takes", data, Long.class);
        return response.getBody();
    }

     /**
     * Запрос DAS-18 Получение поля spc по полю id в таблице course, получение поля ip
     * */
     public String getSpcIpByCourseId(Long courseId){
         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<String> response = restTemplate.getForEntity(dasIp + "/courses/" + courseId + "/spc/ip", String.class, courseId);
         return response.getBody();
     }

    /**
     * Запрос DAS-22C Обновление поля taken по полю id в таблице take
     * */
    public void updateTakenById(Long takeId, Boolean taken){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(dasIp + "/takes/" + takeId + "/taken", HttpMethod.PUT, new HttpEntity<>(taken), Boolean.class, takeId);
    }

    /**
     * DAS-23C Обновление поля takeStatus по полю id в таблице take
     * */
    public void updateTakeStatusById(Long takeId, TakeStatus takeStatus){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(dasIp + "/takes/" + takeId + "/takeStatus", HttpMethod.PUT, new HttpEntity<>(takeStatus), Boolean.class, takeId);
    }

}
