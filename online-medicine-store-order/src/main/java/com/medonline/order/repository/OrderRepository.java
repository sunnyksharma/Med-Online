package com.medonline.order.repository;

import com.medonline.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findByCustomerId(Integer customerId);
}
