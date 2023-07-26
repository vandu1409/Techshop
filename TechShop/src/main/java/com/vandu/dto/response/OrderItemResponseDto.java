package com.vandu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {

	private Long orderItemId;
	
	private Double totalPrice;

	private Long quantity;

	private String productDetailsName;
	
	private Double price;

	private String image;
	
	private boolean feedBack;
}
