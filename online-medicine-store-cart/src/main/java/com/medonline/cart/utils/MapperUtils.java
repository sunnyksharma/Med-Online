package com.medonline.cart.utils;

import com.medonline.cart.dto.CustomerCartDTO;
import com.medonline.cart.dto.MedicineDTO;
import com.medonline.cart.entity.CustomerCart;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MapperUtils {



    public static CustomerCart toCustomerCartEntity(@NotNull CustomerCartDTO customerCartDTO){
        CustomerCart entity = new CustomerCart();
        entity.setCustomerId(customerCartDTO.getCustomerId());
//        entity.setCartId(customerCartDTO.getCartId());
        entity.setQuantity(null!=customerCartDTO.getQuantity()? customerCartDTO.getQuantity() : 1);
        entity.setMedicineId(customerCartDTO.getMedicine().getMedicineId());
        return entity;
    }

    public static CustomerCartDTO toCustomerCartDto(@NotNull CustomerCart customerCart,@NotNull MedicineDTO medicineDTO,String uri) {
        CustomerCartDTO dto = new CustomerCartDTO();
        dto.setCartId(customerCart.getCartId());
        dto.setQuantity(customerCart.getQuantity());
        dto.setCustomerId(customerCart.getCustomerId());

         uri +="/medicine/"+customerCart.getMedicineId();
        medicineDTO = new RestTemplate().getForObject(uri,MedicineDTO.class);
        medicineDTO.setMedicineId(customerCart.getMedicineId());
        dto.setMedicine(medicineDTO);
        return dto;
    }
}
