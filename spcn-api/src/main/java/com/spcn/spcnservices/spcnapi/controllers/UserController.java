package com.spcn.spcnservices.spcnapi.controllers;

import com.spcn.spcnservices.spcnapi.dtos.api.user.*;
import com.spcn.spcnservices.spcnapi.services.api.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    private final UserApiService userApiService;

    @Autowired
    public UserController(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    /**
     * API-1C   Регистрация пользователя -
     *          проверка наличия записи по полю email в таблице usr,
     *          запись не найдена ?
     *          (добавление записи в таблицу usr) :
     *          (ошибка "такой пользовател уже существует")
     * */
    @PostMapping("/users/registration")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody RegisterUserRequestDto data){
        return new ResponseEntity<>(userApiService.registerUser(data), HttpStatus.OK);
    }

    /**
     * API-2C   Авторизация пользователя -
     *          поиск записи по полю email в таблице usr,
     *          запись найдена ?
     *          (password совпадает ?
     *          (сборка объекта пользователя) : (ошибка "неверный пароль")) :
     *          (ошибка "такого пользователя не существует")
     * */
    @PostMapping("/users/auth")
    public ResponseEntity<UserApiDto> authUser(@RequestBody AuthUserRequestDto data){
        return new ResponseEntity<>(userApiService.authUser(data), HttpStatus.OK);
    }

    /**
     * API-3C   Отправка кода для подтверждения восстановления пароля пользователя -
     *          проверка наличия записи по полю email в таблице usr,
     *          запись найдена ?
     *          (отправка письма с кодом на email) :
     *          (ошибка "неверный email")
     * */
    @PostMapping("/users/passwordRecovery")
    public ResponseEntity<HandlePasswordRecoveryResponseDto> handlePasswordRecovery(@RequestBody String email){
        return new ResponseEntity<>(userApiService.handlePasswordRecovery(email), HttpStatus.OK);
    }

    /**
     * API-4C   Изменение пароля пользователя -
     *          проверка наличия записи по полю email в таблице usr,
     *          запись найдена ?
     *          (обновление поля password по полю email в таблице usr) :
     *          (ошибка "неверный email")
     * */
    @PutMapping("/users/passwordChange")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequestDto data){
        userApiService.changePassword(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * API-5C   Изменение имени пользователя –
     *          обновление поля name по полю id в таблице usr
     * */
    @PutMapping("/users/{userId}/nameUpdate")
    public ResponseEntity<Void> updateName(@PathVariable Long userId, @RequestBody String name){
        userApiService.updateName(userId, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * API-6C   Изменение зависимости пользователя -
     *          обновление поля isDependent по полю id в таблице usr
     * */
    @PutMapping("/users/{userId}/dependencyUpdate")
    public ResponseEntity<Void> updateDependency(@PathVariable Long userId, @RequestBody Boolean isDependent){
        userApiService.updateDependency(userId, isDependent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: Будет вызываться реактовским визуализатором для отображения данных о пользователе
    /**
     * API-7C   Получение пользователя -
     *          поиск записи по полю id в таблице usr, сборка объекта пользователя
     * */
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserApiDto> getUser(@PathVariable Long userId){
        return new ResponseEntity<>(userApiService.getUser(userId), HttpStatus.OK);
    }

}
