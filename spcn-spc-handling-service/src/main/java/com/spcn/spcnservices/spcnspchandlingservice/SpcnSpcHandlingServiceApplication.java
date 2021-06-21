package com.spcn.spcnservices.spcnspchandlingservice;

import com.spcn.spcnservices.spcnspchandlingservice.services.shs.SpcRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpcnSpcHandlingServiceApplication implements CommandLineRunner {

    private final SpcRequestsService spcRequestsService;

    @Autowired
    public SpcnSpcHandlingServiceApplication(SpcRequestsService spcRequestsService) {
        this.spcRequestsService = spcRequestsService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpcnSpcHandlingServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        String ip = "http://192.168.1.35";
//
//        Thread.sleep(180000);
//        spcRequestsService.startTake(ip);
//        Thread.sleep(10000);
//        spcRequestsService.endTake(ip);

//        spcRequestsService.startTake(1L);
//        Thread.sleep(60000);
//        spcRequestsService.endTake(1L);
        
    }
}
