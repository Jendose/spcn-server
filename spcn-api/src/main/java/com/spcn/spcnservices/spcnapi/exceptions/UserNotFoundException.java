package com.spcn.spcnservices.spcnapi.exceptions;

public class UserNotFoundException extends IllegalArgumentException {

    public UserNotFoundException() {
        super("Пользователя с таким адресом электронной почты не существует");
    }
}
