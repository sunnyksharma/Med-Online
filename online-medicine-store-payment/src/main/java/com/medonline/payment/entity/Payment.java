package com.medonline.payment.entity;

import com.medonline.payment.dto.PaymentStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
@Builder()
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer paymentId;

    @Column(name = "PAYMENT_TIME")
    LocalDateTime timeOfPayment;
    Float amount;
    String cardId;
    Integer customerId;
    PaymentStatus status;

}
