package com.spcn.spcnservices.spcnspchandlingservice.services.shs;

import com.spcn.spcnservices.spcnspchandlingservice.dtos.das.SaveTakeRequestDto;
import com.spcn.spcnservices.spcnspchandlingservice.dtos.das.TakeDasDto;
import com.spcn.spcnservices.spcnspchandlingservice.dtos.das.TakeStatus;
import com.spcn.spcnservices.spcnspchandlingservice.services.das.DasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class SpcRequestsService {

    private final DasService dasService;

    @Autowired
    public SpcRequestsService(DasService dasService) {
        this.dasService = dasService;
    }

    /**
     * SHS-4S  Вызов оповещающего сигнала дозатора
     * */
    public void startTake(Long courseId){

        String spcIp = dasService.getSpcIpByCourseId(courseId);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(spcIp + "/startTake", Void.class);

        TakeDasDto take = new TakeDasDto();
        take.setDate(LocalDateTime.now());
        take.setTakeStatus(TakeStatus.WAIT);

        Long takeId = dasService.saveTake(new SaveTakeRequestDto(courseId, take));

        // Захардкожено значение false для регистрации потенциальной утери данных
        SpcWaitingService.addWaiting(spcIp, new TakeInfo(takeId, false));
    }

    // Метод для случая, когда дозатор НЕ присылает данные сразу в ответе
//    public void endTake(String ip){
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Void> response = restTemplate.postForEntity(ip, "0", Void.class);
//        System.out.println(response.getStatusCode());
//    }

    /**
     * SHS-5S  Получение данных с дозатора
     * */
    public void endTake(Long courseId){

        String spcIp = dasService.getSpcIpByCourseId(courseId);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Integer> response = restTemplate.getForEntity(spcIp + "/endTake", Integer.class);

        if (response.getBody() != null) {
            boolean taken = response.getBody() > 0;
            TakeInfo takeInfo = SpcWaitingService.getWaiting(spcIp);
            dasService.updateTakenById(takeInfo.takeId, taken);
            dasService.updateTakeStatusById(takeInfo.takeId, TakeStatus.OK);
        }
    }


}
