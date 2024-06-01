package com.medonline.cart.controller;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.dto.MedicineDTO;
import com.medonline.cart.entity.CustomerCart;
import com.medonline.cart.exception.MedOnlineCartException;
import com.medonline.cart.service.CartService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value="/viewCart/{customerId}")
    public ResponseEntity<List<MedicineDTO>> viewMyCart(@PathVariable Integer customerId){
        return new ResponseEntity<>(cartService.viewMedicinesInCart(customerId), HttpStatus.OK);
    }

    @GetMapping(value="/view-medicine-in-cart/{medicineId}/{customerId}")
    public ResponseEntity<MedicineDTO> viewMedicineInCart(@PathVariable Integer medicineId, @PathVariable Integer customerId){
//        return new ResponseEntity<>(cartService.viewMedicinesInCart().stream().filter)
        //TODO: Upcoming feature
        return null;
    }

    @PatchMapping(value="/update-quantity/{medicineId}/{customerId}")
    public ResponseEntity<String> updateQuantity(@PathVariable Integer medicineId, @PathVariable Integer customerId, @RequestBody Integer quantity) throws MedOnlineCartException {
        return new ResponseEntity<>(cartService.modifyQuantity(customerId,medicineId,quantity),HttpStatus.OK);
    }

}