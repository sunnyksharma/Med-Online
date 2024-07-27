package com.medonline.customer.service;

import com.medonline.customer.dto.CustomerDTO;
import org.springframework.security.core.Authentication;

public interface CustomerService {
    void register(CustomerDTO customerDTO);
    Authentication login(String username, String password);
    public CustomerDTO getCustomerByUserName(String userName) throws Exception;
//    public PasswordEncoder passwordEncoder();
    public void updatePassword(String userName,String password);
}
