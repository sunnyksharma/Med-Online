package com.medonline.cart.service;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.dto.MedicineDTO;
import com.medonline.cart.entity.CustomerCart;
import com.medonline.cart.exception.MedOnlineCartException;

import java.util.List;


public interface CartService {
    public CustomerCart addMedicineToCart(CustomerCartDTO cartDTO, Integer customerId, Integer medicineId) throws MedOnlineCartException;
    List<CustomerCartDTO> viewCart(Integer customerId);
    List<MedicineDTO> viewMedicinesInCart(Integer customerId);
    void removeMedicineFromCart(Integer customerId, Integer medicineId) throws MedOnlineCartException ;
    void emptyCart(Integer customerId);
    String modifyQuantity(Integer customerId, Integer medicineId, Integer quantity) throws MedOnlineCartException;


}
