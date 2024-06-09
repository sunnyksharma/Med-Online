package com.medonline.payment.service;

import com.medonline.payment.dto.CardDTO;
import com.medonline.payment.dto.PaymentDTO;
import com.medonline.payment.dto.PaymentStatus;
import com.medonline.payment.entity.Card;
import com.medonline.payment.entity.Payment;
import com.medonline.payment.exception.MedOnlinePaymentException;
import com.medonline.payment.repository.CardRepository;
import com.medonline.payment.repository.PaymentRepository;
import com.medonline.payment.utils.MapperUtils;
import com.medonline.payment.utils.ValidationUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public void addCard(CardDTO cardDTO) throws MedOnlinePaymentException {
        //CHECK IF IT EXISTS
        Optional<Card> optCardIfExists = cardRepository.findById(cardDTO.getCardId());
        if(optCardIfExists.isPresent()){
            throw new MedOnlinePaymentException("Card Already Exists");
        }
        Card card = MapperUtils.toEntity(cardDTO);
        cardRepository.save(card);
        //
    }

    @Override
    public void deleteCard(String cardId) throws MedOnlinePaymentException {
        Card card = cardRepository.findById(cardId).orElseThrow(()->new MedOnlinePaymentException("Card not found"));
        card.setEnabled(false);
    }

    @Override
    public List<CardDTO> viewCards(Integer customerId) {
        List<Card> cards = cardRepository.findByCustomerId(customerId);

        return cards.stream().map(MapperUtils::toDto).toList();
    }

    @Override
    public PaymentDTO getPaymentDetails(Integer paymentId) throws MedOnlinePaymentException {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->new MedOnlinePaymentException("No such payment made"));
        String cardId = payment.getCardId();
        CardDTO cardDto = this.getCardDetails(cardId);
        return MapperUtils.toDto(payment,cardDto);
    }

    @Override
    public CardDTO getCardDetails(String cardId) throws MedOnlinePaymentException {
        return MapperUtils.toDto(cardRepository.findById(cardId).orElseThrow(()->new MedOnlinePaymentException("Card Not found")));

    }

    @Override
    public Integer makePayment(CardDTO cardDTO, Float amount) throws MedOnlinePaymentException {
        Optional<Card> optionalCard  = cardRepository.findById(cardDTO.getCardId());
        Card card = optionalCard.orElseThrow(()->new MedOnlinePaymentException("Card not found"));
        if(ValidationUtils.checkIfCardIsValid(card,cardDTO)){
            throw new MedOnlinePaymentException("Invalid Card");
        }
        Payment payment = Payment.builder()
                .timeOfPayment(LocalDateTime.now())
                .amount(amount)
                .cardId(card.getCardId())
                .status(PaymentStatus.SUCCESSFUL)
                .customerId(card.getCustomerId()).build();

        Payment savedPayment =  paymentRepository.save(payment);
        return savedPayment.getPaymentId();
    }
}
