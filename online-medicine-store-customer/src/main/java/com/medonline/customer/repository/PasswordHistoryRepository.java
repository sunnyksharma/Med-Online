package com.medonline.customer.repository;

import com.medonline.customer.entity.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory,Integer> {
}
