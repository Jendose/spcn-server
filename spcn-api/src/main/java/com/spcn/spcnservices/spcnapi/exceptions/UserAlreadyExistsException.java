package com.spcn.spcnservices.spcnapi.exceptions;

public class UserAlreadyExistsException extends IllegalArgumentException {

    public UserAlreadyExistsException() {
        super("Такой пользователь уже существует");
    }
}
