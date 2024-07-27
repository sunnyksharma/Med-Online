package com.medonline.payment.service;

import com.medonline.payment.dto.CustomerDTO;
import com.medonline.payment.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.imageio.spi.ServiceRegistry;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    WebClient webClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String url = discoveryClient.getInstances("Medonline-Gateway").get(0).getUri() + "/customer/user-name/"+username;
        CustomerDTO customerDTO = webClient.get().uri(url).retrieve().bodyToMono(CustomerDTO.class).block();
        assert customerDTO != null;
        return UserInfo.builder().userName(customerDTO.getCustomerEmailId()).password(customerDTO.getPassword()).build();



    }
}
