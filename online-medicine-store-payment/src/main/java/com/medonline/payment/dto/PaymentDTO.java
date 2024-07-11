package com.medonline.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDTO {
    Integer paymentId;
    LocalDateTime timeOfPayment;
    Float amount;
    CardDTO cardDTO;
    Integer customerId;
    PaymentStatus status;
}
