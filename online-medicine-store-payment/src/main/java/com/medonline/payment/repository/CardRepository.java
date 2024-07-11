package com.medonline.payment.repository;

import com.medonline.payment.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,String> {


    List<Card> findByCustomerId(Integer customerId);
}
