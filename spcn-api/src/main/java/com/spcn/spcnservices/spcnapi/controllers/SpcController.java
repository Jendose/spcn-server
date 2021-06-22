package com.spcn.spcnservices.spcnapi.controllers;

import com.spcn.spcnservices.spcnapi.dtos.api.spc.UpdateUserRequestDto;
import com.spcn.spcnservices.spcnapi.services.api.SpcApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpcController {
    
    private final SpcApiService spcApiService;

    @Autowired
    public SpcController(SpcApiService spcApiService) {
        this.spcApiService = spcApiService;
    }

    /**
     * API-13C  Связывание дозатора и пользователя -
     *          проверка наличия записи по полю serialNumber в таблице spc,
     *          запись найдена ?
     *          (обновление поля user по полю serialNumber в таблице spc) :
     *          (ошибка "дозатор не найден")
     * */
    @PutMapping("/spc/spcOwnerUpdate")
    public ResponseEntity<Void> updateUser(@RequestParam(value = "serialNumber") String serialNumber, @RequestBody UpdateUserRequestDto data){
        spcApiService.updateUser(serialNumber, data.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * API-14C  Разрыв связи дозатора и пользователя -
     *          проверка наличия записи по полю serialNumber в таблице spc,
     *          запись найдена ?
     *          (обновление поля user на null по полю serialNumber в таблице spc) :
     *          (ошибка "дозатор не найден")
     * */
    @PutMapping("/spc/spcOwnerClean")
    public ResponseEntity<Void> cleanUserBySerialNumber(@RequestParam(value = "serialNumber") String serialNumber){
        spcApiService.cleanUser(serialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * API-15C  Получение данных о существовании связи дозатора и пользователя -
     *          проверка наличия записи по полю serialNumber в таблице spc,
     *          запись найдена ?
     *          (проверка поля user на null) :
     *          (ошибка "дозатор не найден")
     * */
    @GetMapping("/spc/ownership")
    public ResponseEntity<Boolean> isScpOwned(@RequestParam(value = "serialNumber") String serialNumber){
        return new ResponseEntity<>(spcApiService.isScpOwned(serialNumber), HttpStatus.OK);
    }

    /**
     * API-16C  Вызов оповещающего сигнала дозатора -
     *          проверка наличия записи по полю serialNumber в таблице spc,
     *          запись найдена ?
     *          (отправка запроса на spcIp) :
     *          (ошибка "дозатор не найден")
     * */
    @PostMapping("/spc/connectionTest")
    public ResponseEntity<Void> testConnection(@RequestParam(value = "serialNumber") String serialNumber){
        spcApiService.testConnection(serialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
