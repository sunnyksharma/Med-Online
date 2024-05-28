package com.medonline.cart.dto;

import lombok.Data;

@Data
public class CustomerCartDTO {

    private Integer cartId;
    private Integer customerId;
    private MedicineDTO medicine;
    private Integer quantity;

}
