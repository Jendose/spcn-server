package com.spcn.spcnservices.spcnapi.services.das;

import com.spcn.spcnservices.spcnapi.dtos.das.UserDasDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDasService {

//    @Value("${das.ip}")
    private final String dasIp = "http://localhost:8080";

    /**
     * Запрос DAS-C1 Добавление записи в таблицу usr
     * */
    public Long saveUser(UserDasDto user){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDasDto> requestEntity = new HttpEntity<>(user, headers);

        ResponseEntity<Long> response = restTemplate.postForEntity(dasIp + "/users", requestEntity, Long.class);
//        ResponseEntity<Long> response = restTemplate.exchange(dasIp + "/users", HttpMethod.POST, new HttpEntity<>(user), Long.class);
        return response.getBody();
    }

    /**
     * Запрос DAS-C2 Проверка наличия записи по полю email в таблице usr
     * */
    public Boolean doesUserWithEmailExist(String email){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(dasIp + "/users/existence?email=", Boolean.class, email);
        return response.getBody();
    }

    /**
     * Запрос DAS-C3 Поиск записи по полю email в таблице usr
     * */
    public UserDasDto getUserByEmail(String email){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDasDto> response = restTemplate.getForEntity(dasIp + "/users?email=" + email, UserDasDto.class, email);
        return response.getBody();
    }

    /**
     * Запрос DAS-C4 Поиск записи по полю id в таблице usr
     * */
    public UserDasDto getUserById(Long userId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDasDto> response = restTemplate.getForEntity(dasIp + "/users/" + userId, UserDasDto.class, userId);
        return response.getBody();
    }

    /**
     * Запрос DAS-C5 Получение поля id по полю email в таблице usr
     * */
    public Long getIdByEmail(String email){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> response = restTemplate.getForEntity(dasIp + "/users/userId?email=", Long.class, email);
        return response.getBody();
    }

    /**
     * Запрос DAS-C55 Получение поля id по полю email в таблице usr
     * */
    public String getNameByEmail(String email){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dasIp + "/users/name?email=", String.class, email);
        return response.getBody();
    }

    /**
     * Запрос DAS-C6 Получение поля password по полю id в таблице usr
     * */
    public String getPasswordById(Long userId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(dasIp + "/users/" + userId + "/password", String.class, userId);
        return response.getBody();
    }

    /**
     * Запрос !DAS-C7 Получение поля isDependent по полю id в таблице usr
     * */
    public Boolean getDependencyById(Long userId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.getForEntity(dasIp + "/users/" + userId + "/dependency", Boolean.class, userId);
        return response.getBody();
    }

    /**
     * Запрос !DAS-C8 Обновление поля isDependent по полю id в таблице usr
     * */
    public Boolean updateDependencyById(Long userId, Boolean isDependent){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(dasIp + "/users/" + userId + "/dependency", HttpMethod.PUT, new HttpEntity<>(isDependent), Boolean.class, userId);
        return response.getBody();
    }

    /**
     * Запрос DAS-C9 Обновление поля password по полю email в таблице usr
     * */
    public Boolean updatePasswordByEmail(String email, String password){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(dasIp + "/users/password?email=", HttpMethod.PUT, new HttpEntity<>(password), Boolean.class, email);
        return response.getBody();
    }

    /**
     * Запрос DAS-C11 Обновление поля name по полю id в таблице usr
     * */
    public Boolean updateNameById(Long userId, String name){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(dasIp + "/users/" + userId + "/name");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(name, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(dasIp + "/users/" + userId + "/name", HttpMethod.PUT, requestEntity, Boolean.class, userId);

        return response.getBody();
    }

}
