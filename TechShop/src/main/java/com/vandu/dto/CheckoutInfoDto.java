package com.vandu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutInfoDto {
	private Long voucherId;

	private String notes;
	
	private int paymentMethod;
}
