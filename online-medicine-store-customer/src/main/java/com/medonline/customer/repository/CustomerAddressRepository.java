package com.medonline.customer.repository;

import com.medonline.customer.entity.Customer;
import com.medonline.customer.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Integer> {

}
