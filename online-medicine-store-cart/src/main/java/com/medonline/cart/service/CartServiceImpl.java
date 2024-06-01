package com.medonline.cart.service;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.dto.MedicineDTO;
import com.medonline.cart.entity.CustomerCart;
import com.medonline.cart.exception.MedOnlineCartException;
import com.medonline.cart.repository.CustomerCartRepository;
import com.medonline.cart.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class CartServiceImpl implements CartService{

    @Autowired
    CustomerCartRepository repository;


    @Override
    public CustomerCart addMedicineToCart(CustomerCartDTO cartDTO, Integer customerId, Integer medicineId) throws MedOnlineCartException {
        //TODO: Authorisation: Check if user is loggedIn;
        List<CustomerCart> entityList = repository.findByCustomerIdAndMedicineId(customerId,medicineId);
        if(!entityList.isEmpty()){
            log.info("Medicine already present in the cart");
            throw new MedOnlineCartException("Medicine already present in the cart");
        }
        log.info("Saving the medicine into customer cart");
        CustomerCart entity = MapperUtils.toCustomerCartEntity(cartDTO);
        return repository.save(entity);
    }

    public List<CustomerCartDTO> viewCart(Integer customerId){
        //TODO: Authorisation: Check if user is loggedIn;
        //TODO: Medicine DTO should be fetched from Medicine MS using medicine Id;
        return repository.findByCustomerId(customerId).stream().map(entity->MapperUtils.toCustomerCartDto(entity,new MedicineDTO())).toList();
    }

    public List<MedicineDTO> viewMedicinesInCart(Integer customerId){
        return this.viewCart(customerId).stream().map(CustomerCartDTO::getMedicine).toList();
    }

    public void removeMedicineFromCart(Integer customerId, Integer medicineId) throws MedOnlineCartException {
        //TODO: Authorisation: Check if user is loggedIn;
        try {
            Optional<CustomerCartDTO> cartDTOOptional = this.viewCart(customerId).stream().filter(cartDto -> cartDto.getMedicine().getMedicineId().equals(medicineId) && cartDto.getQuantity() > 0).findFirst();
            CustomerCartDTO dto = cartDTOOptional.orElseThrow();
            repository.deleteById(dto.getCartId());
        } catch(Exception e){
            log.error(e.getLocalizedMessage());
            throw new MedOnlineCartException(e.getLocalizedMessage());
        }
    }

    public void emptyCart(Integer customerId){
        this.viewCart(customerId);
        repository.deleteAllById(this.viewCart(customerId).stream().map(CustomerCartDTO::getCartId).toList());
    }

    public String modifyQuantity(Integer customerId, Integer medicineId, Integer quantity) throws MedOnlineCartException{
        log.debug(String.format("Modifying quantity of medicine %s for customer %s",medicineId,customerId));
        Optional<CustomerCart> cart = repository.findByCustomerIdAndMedicineId(customerId,medicineId).stream().findFirst();
        if(cart.isEmpty()){
            throw new MedOnlineCartException(MedOnlineCartException.MED_NOT_FOUND);
        }
        cart.get().setQuantity(quantity);
        log.debug("The quantity for the medicine is updated in the cart.");
        return String.format("The quantity for the medicine is updated to %s in the cart",quantity);
    }
}
