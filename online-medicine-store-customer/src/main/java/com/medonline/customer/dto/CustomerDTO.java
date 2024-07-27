package com.medonline.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
//Write the necessary annotations to validate the fields

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerDTO {

	private Integer customerId;
	private String customerName;
	private String customerEmailId;
	private String contactNumber;
	private String password;
	private String gender;
	private LocalDate dateOfBirth;


	private List<CustomerAddressDTO> addressList;
	
	private PrimePlansDTO plan;
	private LocalDate planExpiryDate;
	private Integer healthCoins;
}
