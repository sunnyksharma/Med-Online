package com.medonline.medicine.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/health")
public class HealthController {

    @GetMapping(value="/status")
    public ResponseEntity<String> status(){
        return new ResponseEntity<>("Service is up and running!", HttpStatus.OK);
    }
}
