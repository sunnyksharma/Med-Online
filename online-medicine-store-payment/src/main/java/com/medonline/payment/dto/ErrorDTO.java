package com.medonline.payment.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {
    String message;
    LocalDateTime time;
    HttpStatus status;
}
