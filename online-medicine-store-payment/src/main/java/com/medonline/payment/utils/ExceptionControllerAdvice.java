package com.medonline.payment.utils;

import com.medonline.payment.dto.ErrorDTO;
import com.medonline.payment.exception.MedOnlinePaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MedOnlinePaymentException.class)
    public ResponseEntity<ErrorDTO> handleException(MedOnlinePaymentException exception){
        return new ResponseEntity<>(ErrorDTO.builder().message(exception.getMessage()).time(LocalDateTime.now()).status(HttpStatus.BAD_REQUEST).build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception){
        return new ResponseEntity<>(ErrorDTO.builder().message(exception.getLocalizedMessage()).time(LocalDateTime.now()).status(HttpStatus.INTERNAL_SERVER_ERROR).build(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
