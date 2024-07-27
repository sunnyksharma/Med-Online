package com.medonline.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class CustomerDTO {

    private Integer customerId;
    private String customerName;
    private String customerEmailId;
    private String contactNumber;
    private String password;
    private String gender;
    private LocalDate dateOfBirth;


//    private List<CustomerAddressDTO> addressList;

//    private PrimePlansDTO plan;
    private LocalDate planExpiryDate;
    private Integer healthCoins;
}
