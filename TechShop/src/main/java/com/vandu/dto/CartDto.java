package com.vandu.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
	private Long cartId;

	private Long quantity;

	private Double totalPrice;

	private Date createDate;

	private Long userId;
	
	private Long productDetailsId;
	
//	private Long colorId;
//	
//	private Long productVersionId;
}
