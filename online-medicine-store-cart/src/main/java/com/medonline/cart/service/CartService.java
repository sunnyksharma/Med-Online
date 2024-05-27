package com.medonline.cart.service;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.entity.CustomerCart;
import com.medonline.cart.exception.MedOnlineCartException;


public interface CartService {
    public CustomerCart addMedicineToCart(CustomerCartDTO cartDTO, Integer customerId, Integer medicineId) throws MedOnlineCartException;
}
