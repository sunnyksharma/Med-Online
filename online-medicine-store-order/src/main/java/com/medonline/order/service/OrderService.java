package com.medonline.order.service;

import com.medonline.order.dto.OrderDTO;
import com.medonline.order.exception.MedOnlineException;

import java.util.List;

public interface OrderService {
    List<OrderDTO> viewOrders(Integer customerId) throws MedOnlineException;

    public OrderDTO placeOrder(OrderDTO orderDTO) throws MedOnlineException;

    public void cancelOrder(Integer orderId, String reason) throws MedOnlineException;
}
