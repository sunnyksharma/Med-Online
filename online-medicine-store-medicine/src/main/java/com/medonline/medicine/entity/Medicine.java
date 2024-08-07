package com.medonline.medicine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "MEDICINE")
public class Medicine {

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName + ", manufacturer="
				+ manufacturer + ", price=" + price + ", discountPercent=" + discountPercent + ", manufacturingDate="
				+ manufacturingDate + ", expiryDate=" + expiryDate + ", category=" + category + ", quantity=" + quantity
				+ "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDICINE_ID")
	private Integer medicineId;
	private String medicineName;
	private String manufacturer;
	private Integer price;
	private Integer discountPercent;
	private LocalDate manufacturingDate;
	private LocalDate expiryDate;
	private String category;
	private Integer quantity;
}
