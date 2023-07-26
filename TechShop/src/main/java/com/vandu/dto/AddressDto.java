package com.vandu.dto;

import com.vandu.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

	private Long addressId;
	
	private String fullname;

	private String street;

	private String town;

	private String district;

	private String provice;
	
	private Boolean isActive;
	
	private Boolean isDelete;
	
	private String phone;
	
	private String notes;
	
	private Long voucherId;
	
	private Double discount;

}
