package com.medonline.customer.service;

import com.medonline.customer.dto.CustomerDTO;
import com.medonline.customer.entity.Customer;
import com.medonline.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    CustomerService service;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<Customer> opt = customerRepository.findByCustomerEmailId(username);
            Customer customer= opt.orElseThrow(()->new Exception("CustomerNotFound"));

            //CustomerDTO dto = CustomerDTO.builder().customerName(customer.getCustomerName()).customerEmailId(username).password(customer.getPassword()).customerId(customer.getCustomerId()).contactNumber(customer.getContactNumber()).build();


            return User.builder()
                    .username(customer.getCustomerEmailId())
                    .password(customer.getPassword())
                    .build();

        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getLocalizedMessage());
        }
       // return null;
    }
}
