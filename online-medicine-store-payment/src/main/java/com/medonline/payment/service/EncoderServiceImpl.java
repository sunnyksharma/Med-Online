package com.medonline.payment.service;

import com.medonline.payment.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EncoderServiceImpl {

    @Autowired
    WebClient webClient;

    @Autowired
    DiscoveryClient discoveryClient;


    public PasswordEncoder passwordEncoder(){
        String url = discoveryClient.getInstances("Medonline-Gateway").get(0).getUri() + "/customer/password-encoder";
        return webClient.get().uri(url).retrieve().bodyToMono(PasswordEncoder.class).block();

    }
}
