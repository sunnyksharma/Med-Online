package com.medonline.payment.utils;

import com.medonline.payment.dto.CardDTO;
import com.medonline.payment.dto.PaymentDTO;
import com.medonline.payment.entity.Card;
import com.medonline.payment.entity.Payment;

import java.time.LocalDateTime;

public class MapperUtils {

    public static Card toEntity(CardDTO cardDTO){
        return Card.builder().cardId(cardDTO.getCardId()).cardType(cardDTO.getCardType()).nameOnCard(cardDTO.getNameOnCard())
                .customerId(cardDTO.getCustomerId()).cvv(cardDTO.getCvv()).enabled(true).lastUpdatedTime(LocalDateTime.now()).build();
    }


    public static CardDTO toDto(Card card) {
        return CardDTO.builder().cardId(card.getCardId()).nameOnCard(card.getNameOnCard()).cardType(card.getCardType()).build();
    }

    public static PaymentDTO toDto(Payment payment, CardDTO cardDto){
        return PaymentDTO.builder().paymentId(payment.getPaymentId()).timeOfPayment(payment.getTimeOfPayment()).amount(payment.getAmount()).customerId(payment.getCustomerId()).cardDTO(cardDto).build();
    }
}
