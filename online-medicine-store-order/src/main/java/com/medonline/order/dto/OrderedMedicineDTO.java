package com.medonline.order.dto;

public class OrderedMedicineDTO {
    private Integer orderedMedicineId;
    private Integer orderedQuantity;
    private Double orderSubtotal;
    private Integer customerID;

    public Integer getOrderedMedicineId() {
        return orderedMedicineId;
    }

    public void setOrderedMedicineId(Integer orderedMedicineId) {
        this.orderedMedicineId = orderedMedicineId;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Double getOrderSubtotal() {
        return orderSubtotal;
    }

    public void setOrderSubtotal(Double orderSubtotal) {
        this.orderSubtotal = orderSubtotal;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
}
