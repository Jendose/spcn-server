package com.spcn.spcnservices.spcnapi.services.das;

import com.spcn.spcnservices.spcnapi.dtos.das.CourseDasDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CourseDasService {

//    @Value("${das.ip}")
    private final String dasIp = "http://localhost:8080";

    /**
     * Запрос DAS-15 Добавление записи в таблицу course
     * */
    public Long saveCourse(CourseDasDto course){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> response = restTemplate.postForEntity(dasIp + "/courses", course, Long.class);
        return response.getBody();
    }

    /**
     * Запрос DAS-16 Удаление записи из таблицы course
     * */
    public Boolean deleteCourse(Long courseId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(dasIp + "/courses/" + courseId, HttpMethod.DELETE, null, Boolean.class, courseId);
        return response.getBody();
    }

    /**
     * Запрос DAS-18 Получение поля spc по полю id в таблице course, получение поля ip
     * */

}
