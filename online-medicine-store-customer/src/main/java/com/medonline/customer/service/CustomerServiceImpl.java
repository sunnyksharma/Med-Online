package com.medonline.customer.service;

import com.medonline.customer.dto.CustomerDTO;
import com.medonline.customer.entity.Customer;
import com.medonline.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public void register(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setCustomerEmailId(customerDTO.getCustomerEmailId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        customer.setGender("Male");
        customer.setDateOfBirth(LocalDate.of(1999,7,3));
        customerRepository.save(customer);


    }

    public void updatePassword(String userName,String password){
        Optional<Customer> opt = customerRepository.findByCustomerEmailId(userName);
        Customer customer= opt.orElseThrow(()->new UsernameNotFoundException("CustomerNotFound"));
        customer.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public Authentication login(String username, String password) {
        Authentication request = UsernamePasswordAuthenticationToken.unauthenticated(username,password);
        Authentication authenticatedResponse= authenticationManager.authenticate(request);
        SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext context = strategy.getContext();
        context.setAuthentication(authenticatedResponse);
        strategy.setContext(context);
        return authenticatedResponse;
    }

//    @Autowired
//    SecurityContextHolder securityContextHolder;

    public CustomerDTO getCustomerByUserName(String userName) throws Exception {

//        if(SecurityContextHolder.getContext().getAuthentication().getName().equals(userName)){
//            throw new Exception("Unauthorised");
//        }
        Optional<Customer> opt = customerRepository.findByCustomerEmailId(userName);
        Customer customer= opt.orElseThrow(()->new Exception("CustomerNotFound"));

        return CustomerDTO.builder().customerName(customer.getCustomerName()).customerEmailId(userName).password(customer.getPassword()).customerId(customer.getCustomerId()).contactNumber(customer.getContactNumber()).build();
    }
}
