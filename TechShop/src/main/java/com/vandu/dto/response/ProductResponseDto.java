package com.vandu.dto.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

	private Long productId;

	private String name;

	private Double price;
	
	private String image;

	private Date createDate;

	private Date updateDate;
	
	private boolean isAvailable;
	
	private String productCode;

	private String description;

	private String categoryCode;
	
	private String categoryName;
	
	private Long totalOrder;
	
	private Long totalQuantity;
	
	private List<DiscountResponseDto> discounts;
	
	private Double discountedPrice;
	
	private List<ProductVersionResponseDto> versions;

	private List<DescriptionResponseDto> descriptions;
	
	private List<ProductImageResponseDto> images;
	
	private List<AttributesResponseDto> attributes;
	


	

	
	
	

}
