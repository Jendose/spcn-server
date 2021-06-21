package com.spcn.spcnservices.spcndataaccessservice.controllers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.SpcDasDto;
import com.spcn.spcnservices.spcndataaccessservice.dtos.UserDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Spc;
import com.spcn.spcnservices.spcndataaccessservice.model.User;
import com.spcn.spcnservices.spcndataaccessservice.repositories.SpcRepository;
import com.spcn.spcnservices.spcndataaccessservice.repositories.UserRepository;
import com.spcn.spcnservices.spcndataaccessservice.utils.mappers.SpcMapper;
import com.spcn.spcnservices.spcndataaccessservice.utils.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Transactional
public class SpcController {

    private final SpcRepository spcRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpcController(SpcRepository spcRepository, UserRepository userRepository) {
        this.spcRepository = spcRepository;
        this.userRepository = userRepository;
    }

    /**
     * DAS-24 Добавление записи в таблицу spc
     * */
//    @PostMapping("/spc")
//    public ResponseEntity<Void> saveSpc(@RequestBody Spc spc){
//
//    }

    /**
     * DAS-25 Проверка наличия записи по полю serialNumber в таблице spc
     * */
    @GetMapping("/spc/existence")
    public ResponseEntity<Boolean> doesSpcWithSerialNumberExist(@RequestParam(value = "serialNumber") String serialNumber){
        return new ResponseEntity<>(spcRepository.existsBySerialNumber(serialNumber), HttpStatus.OK);
    }

    /**
     * DAS-26 Проверка наличия поля user по полю serialNumber в таблице spc
     * */
    @GetMapping("/spc/user/existence")
    public ResponseEntity<Boolean> doesSpcWithSerialNumberHaveUser(@RequestParam(value = "serialNumber") String serialNumber){
        User userRef = spcRepository.findUserBySerialNumber(serialNumber).orElse(null);
        return new ResponseEntity<>(userRef != null, HttpStatus.OK);
    }

//    /**
//     * DAS-261 Получение ссылки на запись по полю serialNumber в таблице spc
//     * */
//    @GetMapping("/spc/ref")
//    public ResponseEntity<Spc> getSpcRefBySerialNumber(@RequestParam(value = "serialNumber") String serialNumber){
//        Long spcId = spcRepository.findIdBySerialNumber(serialNumber).orElse(null);
//        if (spcId != null) {
//            Spc spcRef = spcRepository.getById(spcId);
//            return new ResponseEntity<>(spcRef, HttpStatus.OK);
//        }
//        else return new ResponseEntity<>(null, HttpStatus.OK);
//    }


    /**
     * DAS-262 Получение поля id по полю serialNumber в таблице spc
     * */
    @GetMapping("/spc/id")
    public ResponseEntity<Long> getIdBySerialNumber(@RequestParam(value = "serialNumber") String serialNumber){
        return new ResponseEntity<>(spcRepository.findIdBySerialNumber(serialNumber).orElse(null), HttpStatus.OK);
    }


    /**
     * DAS-27 Получение поля ip по полю serialNumber в таблице spc
     * */
    @GetMapping("/spc/ip")
    public ResponseEntity<String> getIpBySerialNumber(@RequestParam(value = "serialNumber") String serialNumber){
        return new ResponseEntity<>(spcRepository.findIpBySerialNumber(serialNumber).orElse(null), HttpStatus.OK);
    }

    /**
     * DAS-28 Обновление поля user по полю serialNumber в таблице spc
     * */
    @PutMapping("/spc/user")
    public ResponseEntity<Boolean> updateUserBySerialNumber(@RequestParam(value = "serialNumber") String serialNumber, @RequestBody Long userId){
        User userRef = userRepository.getById(userId);
        int response = spcRepository.updateUserBySerialNumber(userRef, serialNumber);
        return new ResponseEntity<>(response != 0, HttpStatus.OK);
    }

    /**
     * DAS-29 Очистка поля user по полю serialNumber в таблице spc
     * */
    @PutMapping("/spc/user/cleaning")
    public ResponseEntity<Boolean> cleanUserBySerialNumber(@RequestParam(value = "serialNumber") String serialNumber){
        int response = spcRepository.cleanUserBySerialNumber(serialNumber);
        return new ResponseEntity<>(response != 0, HttpStatus.OK);
    }

    // TODO: Будет вызываться реактовским визуализатором для получения данных о дозаторе, который необходимо визуализировать
    /**
     * DAS-30 Получение записи по полю id в таблице spc
     * */
    @GetMapping("/spc/{spcId}")
    public ResponseEntity<SpcDasDto> getUserById(@PathVariable Long spcId){
        return new ResponseEntity<>(SpcMapper.mapSpcModelToDto(spcRepository.findById(spcId).orElse(null)), HttpStatus.OK);
    }

}
