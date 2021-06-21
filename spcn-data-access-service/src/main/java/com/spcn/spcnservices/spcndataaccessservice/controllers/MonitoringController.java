package com.spcn.spcnservices.spcndataaccessservice.controllers;

import com.spcn.spcnservices.spcndataaccessservice.model.Monitoring;
import com.spcn.spcnservices.spcndataaccessservice.model.User;
import com.spcn.spcnservices.spcndataaccessservice.repositories.MonitoringRepository;
import com.spcn.spcnservices.spcndataaccessservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
public class MonitoringController {

    private final MonitoringRepository monitoringRepository;
    private final UserRepository userRepository;

    @Autowired
    public MonitoringController(MonitoringRepository monitoringRepository, UserRepository userRepository) {
        this.monitoringRepository = monitoringRepository;
        this.userRepository = userRepository;
    }


    /**
     * DAS-12 Добавление записи в таблицу monitoring
     * */
    @PostMapping("/monitoring")
    public ResponseEntity<Void> saveMonitoring(@RequestBody Monitoring monitoring){
        monitoringRepository.saveAndFlush(monitoring);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * DAS-13 Проверка наличия записей по полю spcOwner в таблице monitoring
     * */
    @GetMapping("/monitoring/{spcOwner}/existence")
    public ResponseEntity<Boolean> doesMonitoringWithSpcOwnerExist(@PathVariable Long spcOwner){
        User spcOwnerRef = userRepository.getById(spcOwner);
        return new ResponseEntity<>(monitoringRepository.existsBySpcOwner(spcOwnerRef), HttpStatus.OK);
    }

    /**
     * DAS-14 Получение поля caretaker по полям spcOwner и isHost в таблице monitoring
     * */
//    @GetMapping("/monitoring/{spcOwner}/host")
//    public ResponseEntity<Long> getCaretakerBySpcOwnerAndIsHost(@PathVariable Long spcOwner, @RequestBody Boolean isHost){
//
//    }

}
