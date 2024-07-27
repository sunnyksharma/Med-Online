package com.medonline.customer.controller;

import com.medonline.customer.dto.CustomerDTO;
import com.medonline.customer.dto.LoginRequest;
import com.medonline.customer.entity.Customer;
import com.medonline.customer.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerByCustomerId(@PathVariable Integer customerId){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("Sidhhant Tiwari");
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping("/user-name/{userName}")
    public ResponseEntity<CustomerDTO> getCustomerByUserName(@PathVariable String userName) throws Exception {
        return new ResponseEntity<>(customerService.getCustomerByUserName(userName),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerDTO user){

        customerService.register(user);
        return new ResponseEntity<>("Customer is registered successfully",HttpStatus.CREATED);
    }
//
//    @GetMapping("/password-encoder")
//    public ResponseEntity<PasswordEncoder> passwordEncoderResponseEntity(){
//        return new ResponseEntity<>(customerService.passwordEncoder(),HttpStatus.OK);
//    }

    @Autowired
    SecurityContextRepository securityContextRepository;

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<Authentication> login(@PathVariable String username, @PathVariable String password, HttpServletRequest request, HttpServletResponse response){
        try {
            log.info("Login Request for -> " + username);

            SecurityContextHolderStrategy securityContextHolderStrategy= SecurityContextHolder.getContextHolderStrategy();
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            Authentication authentication = customerService.login(username, password);
            context.setAuthentication(authentication);
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);
            return new ResponseEntity<>(authentication, HttpStatus.OK);
        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UsernamePasswordAuthenticationToken.unauthenticated(username,password));
        }
    }

    @PatchMapping("/change-password/{userName}/{password}")
    public ResponseEntity<String> changePassword(@PathVariable String userName, @PathVariable String password){

        customerService.updatePassword(userName,password);
        return new ResponseEntity<>("Password Updated Successfully", HttpStatus.OK
        );
    }
}
