package com.medonline.cart.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
@Table
public class CustomerCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    private Integer customerId;
    private Integer medicineId;
    private Integer quantity;
}
