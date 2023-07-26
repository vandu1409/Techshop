package com.vandu.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetalisResponseDto {

	private Long productDetailsId;

	private Long quantity;

	private Double importPrice;
	
	private Double price;

	private String image;
	
	private String colorName;
	
	private Long colorId;

//	private boolean isAvailable;

	private boolean checkOutOfStockProducts() {
		return this.getQuantity() <= 0 ? true : false;
	}

	private Double discountedPrice;

	private int quantitySold;
}
