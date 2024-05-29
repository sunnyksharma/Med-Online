package com.medonline.cart.utils;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.dto.MedicineDTO;
import com.medonline.cart.entity.CustomerCart;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MapperUtils {

    public static CustomerCart toCustomerCartEntity(@NotNull CustomerCartDTO customerCartDTO){
        CustomerCart entity = new CustomerCart();
        entity.setCustomerId(customerCartDTO.getCustomerId());
//        entity.setCartId(customerCartDTO.getCartId());
        entity.setQuantity(customerCartDTO.getQuantity());
        entity.setMedicineId(customerCartDTO.getMedicine().getMedicineId());
        return entity;
    }

    public static CustomerCartDTO toCustomerCartDto(@NotNull CustomerCart customerCart,@NotNull MedicineDTO medicineDTO) {
        CustomerCartDTO dto = new CustomerCartDTO();
        dto.setCartId(customerCart.getCartId());
        dto.setQuantity(customerCart.getQuantity());
        dto.setCustomerId(customerCart.getCustomerId());
        dto.setMedicine(medicineDTO);
        return dto;
    }
}
