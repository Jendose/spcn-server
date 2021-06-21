package com.spcn.spcnservices.spcnapi.exceptions;

public class IncorrectUserEmailException extends IllegalArgumentException {

    public IncorrectUserEmailException() {
        super("Указан неверный адрес электронной почты");
    }
}
