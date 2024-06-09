package com.medonline.payment.entity;

import com.medonline.payment.dto.CardType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@Table(name = "Card")
@Data
@Builder
public class Card {

    @Id
    String cardId;

    YearMonth expiryDate;
    String cvv;
    CardType cardType;
    String nameOnCard;

    Integer customerId;

    LocalDateTime lastUpdatedTime;
    Boolean enabled;
}
