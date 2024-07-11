package com.medonline.order.dto;

import java.time.LocalDate;
import java.util.List;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<CustomerAddressDTO> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<CustomerAddressDTO> addressList) {
        this.addressList = addressList;
    }

    public PrimePlansDTO getPlan() {
        return plan;
    }

    public void setPlan(PrimePlansDTO plan) {
        this.plan = plan;
    }

    public LocalDate getPlanExpiryDate() {
        return planExpiryDate;
    }

    public void setPlanExpiryDate(LocalDate planExpiryDate) {
        this.planExpiryDate = planExpiryDate;
    }

    public Integer getHealthCoins() {
        return healthCoins;
    }

    public void setHealthCoins(Integer healthCoins) {
        this.healthCoins = healthCoins;
    }
}
