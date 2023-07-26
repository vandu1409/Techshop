package com.vandu.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVersionResponseDto {
	private Long productVersionId;

	private String versionName;

	private Double price;

	private Double discountedPrice;

	private Long totalOrder;

	private Long totalQuantity;
	
	private List<ProductDetalisResponseDto> productDetails;

}
