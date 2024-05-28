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

    @Override
    public String toString() {
        return "MedicineDTO [medicineId=" + medicineId + ", medicineName=" + medicineName + ", manufacturer="
                + manufacturer + ", price=" + price + ", discountPercent=" + discountPercent + ", manufacturingDate="
                + manufacturingDate + ", expiryDate=" + expiryDate + ", category=" + category + ", quantity=" + quantity
                + "]";
    }


}
