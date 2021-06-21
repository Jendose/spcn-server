package com.spcn.spcnservices.spcnapi.exceptions;

public class SpcNotFoundException extends IllegalArgumentException {

    public SpcNotFoundException(String serialNumber) {
        super("Не удается найти дозатор " + serialNumber + ". Убедитесь, что дозатор подключён к Интернету и сейриный номер введен верно");
    }
}
