package com.medonline.payment.utils;

import com.medonline.payment.dto.CardDTO;
import com.medonline.payment.entity.Card;

public class ValidationUtils {

    public static boolean checkIfCardIsValid(Card card, CardDTO cardDTO){
        boolean valid = false;
        if(card.getNameOnCard().equals(cardDTO.getNameOnCard()) && card.getCardId().equals(cardDTO.getCardId()) && card.getCardType().equals(cardDTO.getCardType()) && cardDTO.getCustomerId().equals(card.getCustomerId())){
            valid = card.getEnabled();
        }
        return valid;
    }
}
