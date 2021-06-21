package com.spcn.spcnservices.spcnapi.services.das;

import com.spcn.spcnservices.spcnapi.dtos.das.SpcDasDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpcDasService {

//    @Value("${das.ip}")
    private final String dasIp = "http://localhost:8080";

    /**
     * Запрос DAS-24 Добавление записи в таблицу spc
     * */

    /**
     * Запрос DAS-25 Проверка наличия записи по полю serialNumber в таблице spc
     * */
    public Boolean doesSpcWithSerialNumberExist(String serialNumber){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(dasIp + "/spc/existence?serialNumber=" + serialNumber, Boolean.class, serialNumber);
        return response.getBody();
    }

    /**
     * Запрос DAS-26 Проверка наличия поля user по полю serialNumber в таблице spc
     * */
    public Boolean doesSpcWithSerialNumberHaveUser(String serialNumber){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(dasIp + "/spc/user/existence?serialNumber=" + serialNumber, Boolean.class, serialNumber);
        return response.getBody();
    }

    /**
     * DAS-261 Получение ссылки на запись по полю serialNumber в таблице spc
     * */
    public SpcDasDto getSpcRefBySerialNumber(String serialNumber){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SpcDasDto> response = restTemplate.getForEntity(dasIp + "/spc/ref?serialNumber=" + serialNumber, SpcDasDto.class, serialNumber);
        return response.getBody();
    }

    /**
     * DAS-262 Получение поля id по полю serialNumber в таблице spc
     * */
    public Long getIdBySerialNumber(String serialNumber){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> response = restTemplate.getForEntity(dasIp + "/spc/id?serialNumber=" + serialNumber, Long.class, serialNumber);
        return response.getBody();
    }

    /**
     * Запрос DAS-263 Получение поля ip по полю serialNumber в таблице spc
     * */
    public String getIpBySerialNumber(String serialNumber){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dasIp + "/spc/ip?serialNumber=" + serialNumber, String.class, serialNumber);
        return response.getBody();
    }

    /**
     * Запрос DAS-28 Обновление поля user по полю serialNumber в таблице spc
     * */
    public Boolean updateUserBySerialNumber(String serialNumber, Long userId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(dasIp + "/spc/user?serialNumber=" + serialNumber, HttpMethod.PUT, new HttpEntity<>(userId), Boolean.class, serialNumber);
        return response.getBody();
    }

    /**
     * Запрос DAS-29 Очистка поля user по полю serialNumber в таблице spc
     * */
    public Boolean cleanUserBySerialNumber(String serialNumber){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(dasIp + "/spc/user/cleaning?serialNumber=" + serialNumber, HttpMethod.PUT, null, Boolean.class, serialNumber);
        return response.getBody();
    }

}
