package com.medonline.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class MedicineDTO {
    private Integer medicineId;

    private String medicineName;

    private String manufacturer;

    @NotNull(message="{medicine.price.notpresent}")
    private Integer price;
    private Integer discountPercent;

    private LocalDate manufacturingDate;

    private LocalDate expiryDate;

    private String category;

    private Integer quantity;

    public Integer getMedicineId() {
        return medicineId;
    }
    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }
    public String getMedicineName() {
        return medicineName;
    }
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }
    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "MedicineDTO [medicineId=" + medicineId + ", medicineName=" + medicineName + ", manufacturer="
                + manufacturer + ", price=" + price + ", discountPercent=" + discountPercent + ", manufacturingDate="
                + manufacturingDate + ", expiryDate=" + expiryDate + ", category=" + category + ", quantity=" + quantity
                + "]";
    }


}
