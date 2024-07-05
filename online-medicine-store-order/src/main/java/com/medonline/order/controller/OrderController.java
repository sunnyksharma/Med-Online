package com.medonline.order.controller;

import com.medonline.order.dto.OrderDTO;
import com.medonline.order.exception.MedOnlineException;
import com.medonline.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Environment environment;

    @GetMapping(value ="/view-order/customer/{customerId}")
    ResponseEntity<List<OrderDTO>> viewOrders(@PathVariable Integer customerId) throws MedOnlineException
    {
        return new ResponseEntity<>(orderService.viewOrders(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/place-order")
    ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) throws MedOnlineException
    {
        return new ResponseEntity<>(orderService.placeOrder(orderDTO),HttpStatus.ACCEPTED);
    }

}
