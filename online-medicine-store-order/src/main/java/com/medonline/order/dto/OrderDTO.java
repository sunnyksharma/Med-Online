package com.medonline.order.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private Integer orderId;
    private Double orderValueBeforeDiscount;
    private Double discountPercentage;
    private Double orderValueAfterDiscount;
    private LocalDateTime deliveryDate;
    private Integer customerId;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    private String cancelReason;
    private Integer deliveryAddressId;
    private String cardId;
    private DeliveryStatus deliveryStatus;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getOrderValueBeforeDiscount() {
        return orderValueBeforeDiscount;
    }

    public void setOrderValueBeforeDiscount(Double orderValueBeforeDiscount) {
        this.orderValueBeforeDiscount = orderValueBeforeDiscount;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getOrderValueAfterDiscount() {
        return orderValueAfterDiscount;
    }

    public void setOrderValueAfterDiscount(Double orderValueAfterDiscount) {
        this.orderValueAfterDiscount = orderValueAfterDiscount;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Integer deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
