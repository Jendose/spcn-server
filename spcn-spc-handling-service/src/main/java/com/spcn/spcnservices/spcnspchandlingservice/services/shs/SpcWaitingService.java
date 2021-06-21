package com.spcn.spcnservices.spcnspchandlingservice.services.shs;

import java.util.HashMap;
import java.util.Map;

public class SpcWaitingService {

    private final static Map<String, TakeInfo> waitingToBeSaved = new HashMap<>();

    public static void addWaiting(String spcIp, TakeInfo takeInfo){
        waitingToBeSaved.put(spcIp, takeInfo);
    }

    public static TakeInfo getWaiting(String spcIp){
        return waitingToBeSaved.get(spcIp);
    }

    public static void deleteWaiting(String spcIp){
        waitingToBeSaved.remove(spcIp);
    }

}
