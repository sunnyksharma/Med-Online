package com.medonline.payment.config;

import com.medonline.payment.service.EncoderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    EncoderServiceImpl encoderService;

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
////        UserDetails user = User.withUsername("John").password(encoder.encode("1234")).roles("USER").build();
////        UserDetails admin = User.withUsername("Sunny").password(encoder.encode("itsme")).roles("ADMIN").build();
//        return new UserDetailsServiceImpl();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf->csrf.disable())
//                .authorizeHttpRequests(auth-> auth.requestMatchers("/payment/card/**").permitAll())
//                .authorizeHttpRequests(auth-> auth.anyRequest().authenticated()).formLogin()
//        http.csrf().disable()
//                .authorizeHttpRequests().anyRequest().authenticated()
//                .and().formLogin().and().build();

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth.requestMatchers("/payment/welcome").permitAll())
                .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return encoderService.passwordEncoder();
    }
}
