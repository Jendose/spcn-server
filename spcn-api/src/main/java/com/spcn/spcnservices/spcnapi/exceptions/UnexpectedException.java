package com.spcn.spcnservices.spcnapi.exceptions;

public class UnexpectedException extends IllegalArgumentException {

    public UnexpectedException() {
        super("Возникла непредвиденная ошибка. Обратитесь в службу поддержки");
    }
}
