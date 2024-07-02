package com.medonline.cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class Health {

    @GetMapping("/check")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Application is working fine");
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ResponseEntity<String> show(){
        return new ResponseEntity<>("Show", HttpStatus.OK);
    }
}
