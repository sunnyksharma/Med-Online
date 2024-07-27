package com.medonline.order.service;

import com.medonline.order.dto.*;
import com.medonline.order.entity.Order;
import com.medonline.order.exception.MedOnlineException;
import com.medonline.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    WebClient webClient;

    ServiceInstance gatewayInstance = discoveryClient.getInstances("Medonline-Gateway").get(0);

    @Override
    public List<OrderDTO> viewOrders(Integer customerId) throws MedOnlineException {
        List<Order> listOfOrders = orderRepository.findByCustomerId(customerId);
        if (listOfOrders.isEmpty()) throw new MedOnlineException("list is empty");
        List<OrderDTO> resultOrderList = new ArrayList<>();
        listOfOrders.forEach(
                (order) ->
                {
                    OrderDTO orderDTO = orderEntityToDTO(order);
                    resultOrderList.add(orderDTO);
                }
        );
        return resultOrderList;
    }

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) throws MedOnlineException {
        CustomerDTO customerRestResponse = restTemplate.getForObject(
                gatewayInstance.getUri() +"/customer/" + orderDTO.getCustomerId(), CustomerDTO.class);
        CardDTO cardRestResponse = restTemplate.getForObject(
                gatewayInstance.getUri()+"/card/" + orderDTO.getCardId(), CardDTO.class);
        webClient.get().uri(gatewayInstance.getUri()+"/customer/"+orderDTO.getCustomerId()).retrieve().bodyToMono(CustomerDTO.class);
        CustomerCartDTO[] customerCartRestResponse = restTemplate.getForObject(
                gatewayInstance.getUri()+"/" + orderDTO.getCustomerId(), CustomerCartDTO[].class);
        assert customerCartRestResponse != null;
        double totalamount = 0D;
        for (CustomerCartDTO customerCart : customerCartRestResponse) {
            restTemplate.put(
                    gatewayInstance.getUri()+"/" + customerCart.getMedicine().getMedicineId() + "/quantity/" + customerCart.getQuantity(), Object.class);
            OrderedMedicineDTO orderedMedicineDTO = new OrderedMedicineDTO();
            orderedMedicineDTO.setCustomerID(customerCart.getCustomerId());
            orderedMedicineDTO.setOrderedQuantity(customerCart.getQuantity());
            orderedMedicineDTO.setOrderedMedicineId(customerCart.getMedicine().getMedicineId());
            double subtotal = (double) (customerCart.getQuantity() * customerCart.getMedicine().getPrice());
            orderedMedicineDTO.setOrderSubtotal(subtotal);
            restTemplate.postForObject(
                    gatewayInstance.getUri()+"/" + orderedMedicineDTO.getOrderedMedicineId(), orderedMedicineDTO, Object.class);
            totalamount += subtotal;

        }
        orderDTO.setOrderValueBeforeDiscount(totalamount);
        String message = restTemplate.postForObject(
                gatewayInstance.getUri()+"/" + orderDTO.getOrderValueBeforeDiscount(), cardRestResponse, String.class);
        orderDTO.setDeliveryStatus(DeliveryStatus.AWAITING_CONFIRMATION);
        orderDTO.setOrderStatus(OrderStatus.PROCESSING);
        orderRepository.save(OrderDTOtoEntity(orderDTO));
        restTemplate.delete(gatewayInstance+"/" + orderDTO.getCustomerId());

        return orderDTO;
    }

    @Override
    public void cancelOrder(Integer orderId, String reason) {

    }

    private OrderDTO orderEntityToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        orderDTO.setDeliveryStatus(order.getDeliveryStatus());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setCancelReason(order.getCancelReason());
        orderDTO.setOrderValueBeforeDiscount(order.getOrderValueBeforeDiscount());
        orderDTO.setOrderValueAfterDiscount(order.getOrderValueAfterDiscount());
        orderDTO.setDiscountPercentage(order.getDiscountPercentage());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setCardId(order.getCardId());
        orderDTO.setDeliveryAddressId(order.getDeliveryAddressId());
        return orderDTO;
    }

    private Order OrderDTOtoEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCardId(orderDTO.getCardId());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setDeliveryAddressId(orderDTO.getDeliveryAddressId());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        order.setDeliveryStatus(orderDTO.getDeliveryStatus());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setOrderValueBeforeDiscount(orderDTO.getOrderValueBeforeDiscount());
        return order;
    }
}
