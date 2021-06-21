package com.spcn.spcnservices.spcnspchandlingservice.controllers;

import com.spcn.spcnservices.spcnspchandlingservice.services.shs.SpcRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SpcRequestsController {

    private final SpcRequestsService spcRequestsService;

    @Autowired
    public SpcRequestsController(SpcRequestsService spcRequestsService) {
        this.spcRequestsService = spcRequestsService;
    }

    // TODO: Будет вызываться реактовским визуализатором для начала приема
    @GetMapping("/courses/{courseId}/startTake")
    public ResponseEntity<Void> startTake(@PathVariable Long courseId){
        spcRequestsService.startTake(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: Будет вызываться реактовским визуализатором для окончания приема
    @GetMapping("/courses/{courseId}/endTake")
    public ResponseEntity<Void> endTake(@PathVariable Long courseId){
        spcRequestsService.endTake(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
