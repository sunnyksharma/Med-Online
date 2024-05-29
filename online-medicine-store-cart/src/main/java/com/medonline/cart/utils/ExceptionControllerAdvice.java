package com.medonline.cart.utils;

import com.medonline.cart.dto.ErrorDTO;
import com.medonline.cart.exception.MedOnlineCartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MedOnlineCartException.class)
    public ResponseEntity<ErrorDTO> handleException(MedOnlineCartException exception){
        ErrorDTO error = ErrorDTO.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(error,error.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception){
        ErrorDTO error = ErrorDTO.builder().message(exception.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(error,error.getStatus());
    }
}
