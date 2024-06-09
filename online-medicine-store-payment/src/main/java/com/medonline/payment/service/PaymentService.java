package com.medonline.payment.service;

import com.medonline.payment.dto.CardDTO;
import com.medonline.payment.dto.PaymentDTO;
import com.medonline.payment.exception.MedOnlinePaymentException;

import java.util.List;

public interface PaymentService {
    public void addCard(CardDTO cardDTO) throws MedOnlinePaymentException;
    public void deleteCard(String cardId) throws MedOnlinePaymentException;
    public List<CardDTO> viewCards(Integer customerId);
    PaymentDTO getPaymentDetails(Integer paymentId) throws MedOnlinePaymentException;
    CardDTO getCardDetails(String cardId) throws MedOnlinePaymentException;
    Integer makePayment(CardDTO cardDTO, Float amount) throws MedOnlinePaymentException;
}
