package com.medonline.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;



@Entity
@Table(name = "CUSTOMER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String customerName;
	@NotNull
	private String customerEmailId;
	private String contactNumber;
	private String password;
	private String gender;
	private LocalDate dateOfBirth; 

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<CustomerAddress> addressList;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "plan_id")
	private PrimePlans plan;
	private LocalDate planExpiryDate;
	private Integer healthCoins;

	public Integer getHealthCoins() {
		return healthCoins;
	}

	public void setHealthCoins(Integer healthCoins) {
		this.healthCoins = healthCoins;
	}
	public LocalDate getPlanExpiryDate() {
		return planExpiryDate;
	}

	public void setPlanExpiryDate(LocalDate planExpiryDate) {
		this.planExpiryDate = planExpiryDate;
	}

	public PrimePlans getPlan() {
		return plan;
	}

	public void setPlan(PrimePlans plan) {
		this.plan = plan;
	}

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

	public List<CustomerAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<CustomerAddress> addressList) {
		this.addressList = addressList;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmailId="
				+ customerEmailId + ", contactNumber=" + contactNumber + ", password=" + password + ", addressList="
				+ addressList + "]";
	}

	
}
