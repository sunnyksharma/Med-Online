package com.medonline.order.utility;

import com.medonline.order.exception.MedOnlineException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ExceptionControllerAdvice {

    @Autowired
    private Environment environment;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException exception) {
        ErrorMessage error = new ErrorMessage();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", ")));
        error.setTimeStamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorMessage error = new ErrorMessage();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getConstraintName());
        error.setTimeStamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MedOnlineException.class)
    public ResponseEntity<ErrorMessage> handleMedOnlineException(MedOnlineException exception) {
        ErrorMessage error = new ErrorMessage();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(environment.getProperty(exception.getMessage()));
        error.setTimeStamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception exception) {
        ErrorMessage error = new ErrorMessage();
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Something went wrong-->>>read error" + exception.getMessage());
        error.setTimeStamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
