package com.spcn.spcnservices.spcnapi.exceptions;

public class InvalidUserPasswordException extends IllegalArgumentException {

    public InvalidUserPasswordException() {
        super("Неверно введён пароль");
    }
}
