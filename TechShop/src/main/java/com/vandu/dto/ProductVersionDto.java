package com.vandu.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVersionDto {
	private Long productVersionId;

	private String versionName;

	private Double price;

//	private boolean isAvailable;

	private Long productId;
	
	private List<ProductDetailsDto> productDetails;
}
