package com.spcn.spcnservices.spcnapi.services.api;

import com.spcn.spcnservices.spcnapi.dtos.api.monitoring.HandleMonitoringNotificationResponseDto;
import com.spcn.spcnservices.spcnapi.dtos.api.monitoring.SaveMonitoringRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.das.MonitoringDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.UserDasDto;
import com.spcn.spcnservices.spcnapi.services.das.MonitoringDasService;
import com.spcn.spcnservices.spcnapi.services.das.UserDasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitoringApiService {
    
    private final MonitoringDasService monitoringDasService;
    private final UserDasService userDasService;

    @Autowired
    public MonitoringApiService(MonitoringDasService monitoringDasService, UserDasService userDasService) {
        this.monitoringDasService = monitoringDasService;
        this.userDasService = userDasService;
    }


    /**
     * API-8S  Связывание пользователей
     *         saveMonitoring(SaveMonitoringRequestDto data)
     *         - data -> MonitoringDasDto monitoring
     *         - Boolean spcOwnerIsDependent = запрос DAS-9 getDependencyByUserId: data.spcOwnerId
     *         - if (spcOwnerIsDependent) {
     *              Boolean monitoringOfSpcOwnerExist = запрос DAS-C13: data.spcOwnerId
     *              if (!monitoringOfSpcOwnerExist) { monitoring.isHost = true }
     *           }
     *         - Boolean success = запрос DAS-С12: monitoring
     *         - return success
     * */
    public void saveMonitoring(SaveMonitoringRequestDto data){
        MonitoringDasDto monitoring = new MonitoringDasDto();
        monitoring.setCaretaker(new UserDasDto(data.getCaretakerId()));
        monitoring.setSpcOwner(new UserDasDto(data.getSpcOwnerId()));
        Boolean spcOwnerIsDependent = userDasService.getDependencyById(data.getSpcOwnerId());
        if (spcOwnerIsDependent) {
            Boolean monitoringOfSpcOwnerExist = monitoringDasService.doesMonitoringWithSpcOwnerExist(data.getSpcOwnerId());
            if (!monitoringOfSpcOwnerExist) {
                monitoring.setIsHost(true);
            }
            monitoringDasService.saveMonitoring(monitoring);
        }

    }

    /**
     * API-9S  Отправка кода для подтверждения опеки над зарегистрированным пользователом
     * */
    public HandleMonitoringNotificationResponseDto handleMonitoringNotification(String email){
        return null;
    }

}
