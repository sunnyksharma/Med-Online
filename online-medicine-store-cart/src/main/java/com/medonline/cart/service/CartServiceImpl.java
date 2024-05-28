package com.medonline.cart.service;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.entity.CustomerCart;
import com.medonline.cart.exception.MedOnlineCartException;
import com.medonline.cart.repository.CustomerCartRepository;
import com.medonline.cart.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Transactional
@Service
@Slf4j
public class CartServiceImpl implements CartService{

    @Autowired
    CustomerCartRepository repository;


    @Override
    public CustomerCart addMedicineToCart(CustomerCartDTO cartDTO, Integer customerId, Integer medicineId) throws MedOnlineCartException {
        List<CustomerCart> entityList = repository.findByCustomerIdAndMedicineId(customerId,medicineId);
        if(!entityList.isEmpty()){
            log.info("Medicine already present in the cart");
            throw new MedOnlineCartException("Medicine already present in the cart");
        }
        log.info("Saving the medicine into customer cart");
        CustomerCart entity = MapperUtils.toCustomerCartEntity(cartDTO);
        return repository.save(entity);
    }
}
