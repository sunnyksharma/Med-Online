package com.medonline.payment.controller;

import com.medonline.payment.dto.CardDTO;
import com.medonline.payment.dto.PaymentDTO;
import com.medonline.payment.exception.MedOnlinePaymentException;
import com.medonline.payment.service.PaymentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
@Slf4j
@Validated
public class PaymentAPI {

    @Autowired
    PaymentService paymentService;

    @GetMapping("welcome")
    public ResponseEntity<String> welcomePage(){
        return new ResponseEntity<>("Welcome to the payment page",HttpStatus.OK);
    }

    @PostMapping(value = "/amount/{amount}")

    public ResponseEntity<Integer> makePayment(@Valid @RequestBody CardDTO cardDTO, @PathVariable Float amount) throws MedOnlinePaymentException {
        return new ResponseEntity<>(paymentService.makePayment(cardDTO,amount), HttpStatus.ACCEPTED);
    }

    @GetMapping("/details/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentDetails(@PathVariable Integer paymentId) throws MedOnlinePaymentException {
        return new ResponseEntity<>(paymentService.getPaymentDetails(paymentId),HttpStatus.OK);
    }

    @GetMapping("/card/{cardId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CardDTO> getCardDetails(@PathVariable String cardId) throws MedOnlinePaymentException {
        return new ResponseEntity<>(paymentService.getCardDetails(cardId),HttpStatus.OK);
    }

    @PostMapping("/add-card")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> addCard(@Valid @RequestBody CardDTO cardDTO) throws MedOnlinePaymentException {
        log.info("Card adding request received" );
        paymentService.addCard(cardDTO);
        return new ResponseEntity<>("Card is added",HttpStatus.ACCEPTED);
    }

    @PatchMapping(value="/delete-card/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable String cardId) throws MedOnlinePaymentException {
        paymentService.deleteCard(cardId);
        return new ResponseEntity<>("Card is removed",HttpStatus.OK);
    }
}
