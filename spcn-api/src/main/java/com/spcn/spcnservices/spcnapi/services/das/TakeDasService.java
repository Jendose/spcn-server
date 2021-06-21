package com.spcn.spcnservices.spcnapi.services.das;

import com.spcn.spcnservices.spcnapi.dtos.das.TakeDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.TakeListDasDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TakeDasService {

//    @Value("${das.ip}")
    private final String spcIp = "http://localhost:8080";

    /**
     * Запрос DAS-21 Поиск записей по полю course в таблице take
     * */
    public List<TakeDasDto> getTakesByCourseId(Long courseId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TakeListDasDto> response = restTemplate.getForEntity(spcIp + "/courses/" + courseId + "/takes", TakeListDasDto.class, courseId);
        if (response.getBody() != null) return response.getBody().getTakeList();
        else return null;
    }
    
}
