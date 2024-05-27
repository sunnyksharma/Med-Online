package com.medonline.cart.service;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.entity.CustomerCart;
import com.medonline.cart.exception.MedOnlineCartException;
import com.medonline.cart.repository.CustomerCartRepository;
import com.medonline.cart.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CustomerCartRepository repository;


    @Override
    public CustomerCart addMedicineToCart(CustomerCartDTO cartDTO, Integer customerId, Integer medicineId) throws MedOnlineCartException {
        List<CustomerCart> entityList = repository.findByCustomerIdAndMedicineId(customerId,medicineId);
        if(!entityList.isEmpty()){
            throw new MedOnlineCartException("Medicine already present in the cart");
        }
        CustomerCart entity = MapperUtils.toCustomerCartEntity(cartDTO);
        CustomerCart savedEntity = repository.save(entity);
        return savedEntity;
    }
}
