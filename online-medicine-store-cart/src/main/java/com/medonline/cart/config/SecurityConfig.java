package com.medonline.cart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll()
                        .requestMatchers("/cart/viewCart/**").permitAll()
                        .requestMatchers("/eureka/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oAuth->oAuth.jwt(Customizer.withDefaults()))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    @Bean
    public JwtDecoder jwtDecoder(){
        var secretKey = "3cfdaso9786asdf896d98f76as98f686dfasg76976s8f976";
        var spec= new SecretKeySpec(secretKey.getBytes(),"");
        return NimbusJwtDecoder.withSecretKey(spec).macAlgorithm(MacAlgorithm.HS256).build();
    }





}
