package com.vandu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

	private Long addressId;

	private String fullname;

	private String phone;

	private String street;

	private String town;

	private String district;

	private String provice;

	private Boolean isActive;

	private Boolean isDelete;

	public String getAddress() {

		return this.getStreet() + "	," + this.getTown() + "," + this.getDistrict() + "," + this.getProvice();
	}
}
