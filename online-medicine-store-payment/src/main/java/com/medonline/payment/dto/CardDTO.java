package com.medonline.payment.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Data
@Builder
public class CardDTO {
    @NotNull
    String cardId;

    @NotNull
    YearMonth expiryDate;
    String cvv;
    CardType cardType;
    String nameOnCard;

    Integer customerId;

    LocalDateTime lastUpdatedTime;
    Boolean enabled;

}
