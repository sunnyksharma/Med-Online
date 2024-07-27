package com.medonline.customer.entity;


import com.medonline.customer.dto.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="PASSWORD_HISTORY")
public class PasswordHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer customerId;
	private RoleEnum role;
	private String password;
	private boolean enabled;

	
}
