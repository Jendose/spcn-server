package com.spcn.spcnservices.spcnapi.services.api;

import com.spcn.spcnservices.spcnapi.dtos.api.spc.UpdateUserRequestDto;
import com.spcn.spcnservices.spcnapi.exceptions.SpcNotFoundException;
import com.spcn.spcnservices.spcnapi.exceptions.UnexpectedException;
import com.spcn.spcnservices.spcnapi.services.das.SpcDasService;
import com.spcn.spcnservices.spcnapi.services.spc.SpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpcApiService {

    private final SpcDasService spcDasService;
    private final SpcService spcService;

    @Autowired
    public SpcApiService(SpcDasService spcDasService, SpcService spcService) {
        this.spcDasService = spcDasService;
        this.spcService = spcService;
    }

    /**
     * API-13S Связывание дозатора и пользователя
     *         updateUser(String serialNumber, Long userId)
     *         - Boolean spcExist = запрос DAS-25: serialNumber
     *         - if (spcExist) {
     *             Boolean success = запрос DAS-28: serialNumber, userId
     *           }
     *           else { ошибка "дозатор не найден" }
     *         - return success
     * */
    public void updateUser(String serialNumber, Long userId){
        System.out.println(serialNumber + " " + userId);
        Boolean success = spcDasService.updateUserBySerialNumber(serialNumber, userId);
        if (!success) throw new UnexpectedException();
    }

    /**
     * API-14S Разрыв связи дозатора и пользователя
     *         cleanUserBySerialNumber(String serialNumber)
     *         - Boolean spcExist = запрос DAS-25: serialNumber
     *         - if (spcExist) {
     *             Boolean success = запрос DAS-29: serialNumber
     *           }
     *           else { ошибка "дозатор не найден" }
     *         - return success
     * */
    public void cleanUser(String serialNumber){
        Boolean success = spcDasService.cleanUserBySerialNumber(serialNumber);
        if (!success) throw new UnexpectedException();
    }

    /**
     * API-15S Получение данных о существовании связи дозатора и пользователя
     *         isScpOwned(String serialNumber)
     *         - Boolean spcExist = запрос DAS-25: serialNumber
     *         - if (spcExist) {
     *             Boolean isSpcOwned = запрос DAS-26: serialNumber
     *           }
     *           else { ошибка "дозатор не найден" }
     *         - return isSpcOwned
     * */
    public Boolean isScpOwned(String serialNumber){
        Boolean spcExist = spcDasService.doesSpcWithSerialNumberExist(serialNumber);
        if (spcExist) {
            return spcDasService.doesSpcWithSerialNumberHaveUser(serialNumber);
        }
        else throw new SpcNotFoundException(serialNumber);
    }

    /**
     * API-16S Вызов оповещающего сигнала дозатора
     *         testConnection(String serialNumber)
     *         - Boolean spcExist = запрос DAS-C20: serialNumber
     *         - if (spcExist) {
     *             String spcIp = запрос DAS-263: serialNumber
     *             Запрос на spcIp (?)
     *           }
     *           else { ошибка "дозатор не найден" }
     *         - return success
     * */
    public void testConnection(String serialNumber){
        Boolean spcExist = spcDasService.doesSpcWithSerialNumberExist(serialNumber);
        if (spcExist) {
            String spcIp = spcDasService.getIpBySerialNumber(serialNumber);
            Boolean success = spcService.testConnection(spcIp);
            if (!success) throw new UnexpectedException();
        }
        else throw new SpcNotFoundException(serialNumber);
    }

}
