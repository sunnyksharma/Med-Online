package com.medonline.payment.controller;

import com.medonline.payment.dto.CardDTO;
import com.medonline.payment.dto.PaymentDTO;
import com.medonline.payment.exception.MedOnlinePaymentException;
import com.medonline.payment.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentAPI {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/amount/{amount}")
    public ResponseEntity<Integer> makePayment(@RequestBody CardDTO cardDTO, @PathVariable Float amount) throws MedOnlinePaymentException {
        return new ResponseEntity<>(paymentService.makePayment(cardDTO,amount), HttpStatus.ACCEPTED);
    }

    @GetMapping("/details/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentDetails(@PathVariable Integer paymentId) throws MedOnlinePaymentException {
        return new ResponseEntity<>(paymentService.getPaymentDetails(paymentId),HttpStatus.OK);
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<CardDTO> getCardDetails(@PathVariable String cardId) throws MedOnlinePaymentException {
        return new ResponseEntity<>(paymentService.getCardDetails(cardId),HttpStatus.OK);
    }

    @PostMapping("/add-card")
    public ResponseEntity<String> addCard(@RequestBody CardDTO cardDTO) throws MedOnlinePaymentException {
        paymentService.addCard(cardDTO);
        return new ResponseEntity<>("Card is added",HttpStatus.ACCEPTED);
    }

    @PatchMapping(value="/delete-card/{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable String cardId) throws MedOnlinePaymentException {
        paymentService.deleteCard(cardId);
        return new ResponseEntity<>("Card is removed",HttpStatus.OK);
    }
}
