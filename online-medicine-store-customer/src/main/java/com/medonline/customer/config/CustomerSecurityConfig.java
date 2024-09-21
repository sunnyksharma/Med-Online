package com.medonline.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class CustomerSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }

    @Bean
    public SecurityContextRepository securityContextRepository(){
        SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

        return securityContextRepository;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->{

                    auth.requestMatchers("/customer/login/**").permitAll()
                            .requestMatchers("/customer/user/**").hasAuthority("USER")
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/admin").permitAll()
                                    .requestMatchers("/customer/user-name/**").access((auth1,context)->
                                         new AuthorizationDecision(auth1.get().getName().equals(Arrays.stream(context.getRequest().getRequestURI().split("/")).filter(x->x.equals(auth1.get().getName())).findAny().orElse("")))
                            );

                    auth.anyRequest().authenticated();
                })
                .logout(logout->logout.logoutUrl("/customer/logout").clearAuthentication(true))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .securityContext(s->{
                    s.requireExplicitSave(true);
                    s.securityContextRepository(securityContextRepository());
                })
         .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
