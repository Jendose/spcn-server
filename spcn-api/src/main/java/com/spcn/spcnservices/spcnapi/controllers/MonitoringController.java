package com.spcn.spcnservices.spcnapi.controllers;

import com.spcn.spcnservices.spcnapi.dtos.api.monitoring.HandleMonitoringNotificationRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.api.monitoring.HandleMonitoringNotificationResponseDto;
import com.spcn.spcnservices.spcnapi.dtos.api.monitoring.SaveMonitoringRequestDto;
import com.spcn.spcnservices.spcnapi.services.api.MonitoringApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

    private final MonitoringApiService monitoringApiService;

    @Autowired
    public MonitoringController(MonitoringApiService monitoringApiService) {
        this.monitoringApiService = monitoringApiService;
    }

    /**
     * API-8C   Связывание пользователей -
     *          поиск записи в таблице usr где userId==spcOwnerId,
     *          isDependent==false ?
     *          (добавление записи в таблицу monitoring) :
     *          (поиск записи в таблице monitoring где spcOwner==spcOwnerId,
     *          записи не найдены ?
     *          (добавление записи в таблицу monitoring со значением true поля isHost) :
     *          (добавление записи в таблицу monitoring))
     * */
    @PostMapping("/monitoring")
    public ResponseEntity<Void> saveMonitoring(@RequestBody SaveMonitoringRequestDto data){
        monitoringApiService.saveMonitoring(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * API-9C   Отправка кода для подтверждения опеки над зарегистрированным пользователом -
     *          проверка наличия записи по полю email в таблице usr,
     *          запись найдена ?
     *          (isDependent==false ?
     *          (отправка письма с кодом на email) :
     *          (поиск записи в таблице monitoring где spcOwner==userId && isHost==true,
     *          отправка письма с кодом на email пользователя с caretakerId)) :
     *          (ошибка "неверный email")
     * */
    @PostMapping("/monitoring/notification")
    public ResponseEntity<HandleMonitoringNotificationResponseDto> handleMonitoringNotification(@RequestBody HandleMonitoringNotificationRequestDto data){
        return new ResponseEntity<>(monitoringApiService.handleMonitoringNotification(data.getEmail()), HttpStatus.OK);
    }

}
