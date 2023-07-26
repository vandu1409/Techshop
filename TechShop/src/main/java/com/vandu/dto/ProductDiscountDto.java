package com.vandu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDiscountDto {
	
	private Long id;
	
	private Long productId;
	
	private Long discountId;
	

}
