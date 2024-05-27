package com.medonline.cart.utils;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.entity.CustomerCart;
import jakarta.validation.constraints.NotNull;

public class MapperUtils {

    public static CustomerCart toCustomerCartEntity(@NotNull CustomerCartDTO customerCartDTO){
        CustomerCart entity = new CustomerCart();
        entity.setCustomerId(customerCartDTO.getCustomerId());
//        entity.setCartId(customerCartDTO.getCartId());
        entity.setQuantity(customerCartDTO.getQuantity());
        entity.setMedicineId(customerCartDTO.getMedicine().getMedicineId());
        return entity;
    }
}
