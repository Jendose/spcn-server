package com.spcn.spcnservices.spcnapi.services.api;

import com.spcn.spcnservices.spcnapi.dtos.api.user.*;
import com.spcn.spcnservices.spcnapi.dtos.das.UserDasDto;
import com.spcn.spcnservices.spcnapi.exceptions.*;
import com.spcn.spcnservices.spcnapi.services.das.UserDasService;
import com.spcn.spcnservices.spcnapi.utils.mappers.UserMapper;
import com.spcn.spcnservices.spcnapi.utils.CodeGenerator;
//import com.spcn.spcnservices.spcnapi.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserApiService {
    
    private final UserDasService userDasService;
//    private final MailSender mailSender;

    @Autowired
    public UserApiService(UserDasService userDasService/*, MailSender mailSender*/) {
        this.userDasService = userDasService;
//        this.mailSender = mailSender;
    }

    /**
     * API-1S  Регистрация пользователя
     *         registerUser(RegisterUserRequestDto data)
     *         - Boolean userExist = запрос DAS-2: data.email
     *         - if (!userExist) {
     *             data -> UserDasDto user
     *             Long userId = запрос DAS-1: user
     *           }
     *         - else { ошибка "такой пользовател уже существует" }
     *         - return new RegisterUserResponseDto(userId)
    */
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto data){
        Boolean userExist = userDasService.doesUserWithEmailExist(data.getEmail());
        if (!userExist) {
            UserDasDto user = new UserDasDto();
            user.setName(data.getUsername());
            user.setEmail(data.getEmail());
            user.setPassword(data.getPassword());
            user.setIsDependent(data.getIsDependent());
            Long userId = userDasService.saveUser(user);
            return new RegisterUserResponseDto(userId);
        }
        else {
            throw new UserAlreadyExistsException();
        }
    }

    /**
     * API-2S  Авторизация пользователя
     *         authUser(AuthUserRequestDto data)
     *         - UserDasDto user = запрос DAS-3: data.email
     *         - if (user != null) {
     *             if (password совпадает) { user -> UserApiDto userDto // UserDasToApiMapper }
     *             else { ошибка "неверный пароль" }
     *           }
     *           else { ошибка "такого пользователя не существует" }
     *         - return userDto
     */
    public UserApiDto authUser(AuthUserRequestDto data){
        UserDasDto user = userDasService.getUserByEmail(data.getEmail());
        if (user != null){
            if(user.getPassword().equals(data.getPassword())){
                return UserMapper.mapUserModelToDto(user);
            }
            else throw new InvalidUserPasswordException();
        }
        else throw new UserNotFoundException();
    }

    /**
     * API-3S  Отправка кода для подтверждения восстановления пароля пользователя
     */
    public HandlePasswordRecoveryResponseDto handlePasswordRecovery(String email){
        Boolean userExist = userDasService.doesUserWithEmailExist(email);
        if (userExist) {
            String code = CodeGenerator.getRandomCode();
            String username = userDasService.getNameByEmail(email);
            String subject = "SPCN – восстановление пароля";
            String message = String.format(
                    "Здравствуйте, %s \n" +
                            "Ваш код для восстановления пароля: \n" +
                            "%s",
                    username,
                    code
            );
//            mailSender.send(email, subject, message);
            return new HandlePasswordRecoveryResponseDto(code);
        }
        else throw new IncorrectUserEmailException();
    }

    /**
     * API-4S  Изменение пароля пользователя
     */
    public void changePassword(ChangePasswordRequestDto data){
        Boolean success = userDasService.updatePasswordByEmail(data.getEmail(), data.getPassword());
        if (!success) throw new IncorrectUserEmailException();
    }

    /**
     * API-5S  Изменение имени пользователя
     *         updateName(Long userId, String name)
     *         - Boolean success = запрос DAS-11: userId
     *         - return success
     */
    public void updateName(Long userId, String name){
        Boolean success = userDasService.updateNameById(userId, name);
        if (!success) throw new UnexpectedException();
    }

    /**
     * API-6S  Изменение зависимости пользователя
     *         updateDependency(Long userId, Boolean isDependent)
     *         - Boolean success = запрос DAS-9 updateDependencyByUserId: userId, isDependent
     *         - return success
     */
    public void updateDependency(Long userId, Boolean isDependent){
        Boolean success = userDasService.updateDependencyById(userId,isDependent);
        if (!success) throw new UnexpectedException();
    }

    /**
     * API-7S  Получение пользователя
     *         getUser(Long userId)
     *         - UserDasDto user = запрос DAS-4: userId
     *         - user -> UserApiDto userDto
     *         - return userDto
     */
    public UserApiDto getUser(Long userId){
        UserDasDto user = userDasService.getUserById(userId);
        if (user != null){
            System.out.println(user.toString());
            return UserMapper.mapUserModelToDto(user);
        }
        else throw new UnexpectedException();
    }

//    public UserApiDto authUser(AuthUserRequestDto data){
//        UserDasDto user = userDasService.getUserByEmail(data.getEmail());
//        if (user != null){
//            if(user.getPassword().equals(data.getPassword())){
//                return UserMapper.mapUserModelToDto(user);
//            }
//            else throw new InvalidUserPasswordException();
//        }
//        else throw new UserNotFoundException();
//    }
}
