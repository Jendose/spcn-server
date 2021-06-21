package com.spcn.spcnservices.spcnapi.services.das;

import com.spcn.spcnservices.spcnapi.dtos.das.MonitoringDasDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MonitoringDasService {

//    @Value("${das.ip}")
    private final String spcIp = "http://localhost:8080";

    /**
     * Запрос DAS-12 Добавление записи в таблицу monitoring
     * */
    public void saveMonitoring(MonitoringDasDto monitoring){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(spcIp + "/monitoring", monitoring, Boolean.class);
    }

    /**
     * Запрос DAS-13 Проверка наличия записей по полю spcOwner в таблице monitoring
     * */
    public Boolean doesMonitoringWithSpcOwnerExist(Long spcOwner){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(spcIp + "/monitoring/"+ spcOwner +"/existence", Boolean.class, spcOwner);
        return response.getBody();
    }

    /**
     * Запрос DAS-14 Получение поля caretaker по полям spcOwner и isHost в таблице monitoring
     * */
    public Boolean getCaretakerBySpcOwnerAndIsHost(Long spcOwner, Boolean isHost){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(spcIp + "/monitoring/"+ spcOwner +"/host", HttpMethod.GET, new HttpEntity<>(isHost), Boolean.class, spcOwner);
        return response.getBody();
    }

}
