package com.medonline.cart.repository;

import com.medonline.cart.entity.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerCartRepository extends JpaRepository<CustomerCart,Integer> {
    List<CustomerCart> findByCustomerId(Integer customerId);
    List<CustomerCart> findByCustomerIdAndMedicineId(Integer customerId, Integer medicineId);
}
