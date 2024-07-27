package com.medonline.customer.repository;

import com.medonline.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCustomerEmailId(String customerEmailId);
}
