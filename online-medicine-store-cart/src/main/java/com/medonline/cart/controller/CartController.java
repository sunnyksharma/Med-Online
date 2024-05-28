package com.medonline.cart.controller;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.entity.CustomerCart;
import com.medonline.cart.exception.MedOnlineCartException;
import com.medonline.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping(value = "/cart")
class CartController{

    @Autowired
    CartService cartService;

    @PostMapping(value = "/add-medicine/{medicineId}/{customerId}")
    public ResponseEntity<String> addMedicinesToCart(@RequestBody CustomerCartDTO customerCartDTO, @PathVariable Integer medicineId, @PathVariable Integer customerId)
            throws MedOnlineCartException {
        // call service class to add medicines to cart
        CustomerCart cart = cartService.addMedicineToCart(customerCartDTO,customerId,medicineId);
        log.info(cart.toString());
        return ResponseEntity.ok("Medicine Added to Cart");
    }
}