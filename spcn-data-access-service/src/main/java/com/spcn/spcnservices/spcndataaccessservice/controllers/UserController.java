package com.spcn.spcnservices.spcndataaccessservice.controllers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.MonitoringDasDto;
import com.spcn.spcnservices.spcndataaccessservice.dtos.UserDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Monitoring;
import com.spcn.spcnservices.spcndataaccessservice.model.User;
import com.spcn.spcnservices.spcndataaccessservice.repositories.UserRepository;
import com.spcn.spcnservices.spcndataaccessservice.utils.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@Transactional
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * DAS-1C Добавление записи в таблицу usr
     * */
    @PostMapping("/users")
    public ResponseEntity<Long> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userRepository.saveAndFlush(user).getId(), HttpStatus.OK);
    }

    /**
     * DAS-2C Проверка наличия записи по полю email в таблице usr
     * */
    @GetMapping("/users/existence")
    public ResponseEntity<Boolean> doesUserWithEmailExist(@RequestParam(value = "email") String email){
        return new ResponseEntity<>(userRepository.existsByEmail(email), HttpStatus.OK);
    }

    /**
     * DAS-3C Поиск записи по полю email в таблице usr
     * */
    @GetMapping("/users")
    public ResponseEntity<UserDasDto> getUserByEmail(@RequestParam(value = "email") String email){
        return new ResponseEntity<>(UserMapper.mapUserModelToDto(userRepository.findByEmail(email).orElse(null)), HttpStatus.OK);
    }

    /**
     * DAS-4C Поиск записи по полю id в таблице usr
     * */
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDasDto> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(UserMapper.mapUserModelToDto(userRepository.findById(userId).orElse(null)), HttpStatus.OK);
    }

    /**
     * DAS-5C Получение поля id по полю email в таблице usr
     * */
    @GetMapping("/users/userId")
    public ResponseEntity<Long> getIdByEmail(@RequestParam(value = "email") String email){
        return new ResponseEntity<>(userRepository.findIdByEmail(email).orElse(null), HttpStatus.OK);
    }

    /**
     * DAS-55C Получение поля name по полю email в таблице usr
     * */
    @GetMapping("/users/name")
    public ResponseEntity<String> getNameByEmail(@RequestParam(value = "email") String email){
        return new ResponseEntity<>(userRepository.findNameByEmail(email).orElse(null), HttpStatus.OK);
    }

    /**
     * DAS-7C Получение поля password по полю id в таблице usr
     * */
    @GetMapping("/users/{userId}/password")
    public ResponseEntity<String> getPasswordById(@PathVariable Long userId){
        return new ResponseEntity<>(userRepository.findPasswordById(userId).orElse(null), HttpStatus.OK);
    }

    /**
     * DAS-8C Получение поля isDependent по полю id в таблице usr
     * */
    @GetMapping("/users/{userId}/dependency")
    public ResponseEntity<Boolean> getDependencyById(@PathVariable Long userId){
        return new ResponseEntity<>(userRepository.findIsDependentById(userId).orElse(null), HttpStatus.OK);
    }

    /**
     * DAS-9C Обновление поля isDependent по полю id в таблице usr
     * */
    @PutMapping("/users/{userId}/dependency")
    public ResponseEntity<Boolean> updateDependencyById(@PathVariable Long userId, @RequestBody Boolean isDependent){
        int responseInt = userRepository.updateIsDependentById(isDependent, userId);
        return new ResponseEntity<>(responseInt != 0, HttpStatus.OK);
    }

    /**
     * DAS-10C Обновление поля password по полю email в таблице usr
     * */
    @PutMapping("/users/password")
    public ResponseEntity<Boolean> updatePasswordByEmail(@RequestParam(value = "email") String email, @RequestBody String password){
        int responseInt = userRepository.updatePasswordByEmail(password, email);
        return new ResponseEntity<>(responseInt != 0, HttpStatus.OK);
    }

    /**
     * DAS-11C Обновление поля name по полю id в таблице usr
     * */
    @PutMapping("/users/{userId}/name")
    public ResponseEntity<Boolean> updateNameById(@PathVariable Long userId, @RequestBody String name){
        int responseInt = userRepository.updateNameById(name, userId);
        return new ResponseEntity<>(responseInt != 0, HttpStatus.OK);
    }

}
