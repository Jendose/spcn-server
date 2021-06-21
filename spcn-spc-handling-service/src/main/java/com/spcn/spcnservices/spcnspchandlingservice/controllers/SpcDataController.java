package com.spcn.spcnservices.spcnspchandlingservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// Этот контроллер не нужен, если дозатор присылает данные сразу в ответе
@RestController
public class SpcDataController {

    @GetMapping("/saveData")
    public ResponseEntity<?> saveData(@RequestParam(value = "gercon") Integer gercon, HttpServletRequest request){
        String ip = request.getRemoteAddr();
        System.out.println("IP: " + ip);
        System.out.println("gercon: " + gercon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
