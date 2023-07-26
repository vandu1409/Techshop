package com.vandu.dto.response;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.DeclareAnnotation;

import lombok.Data;

@Data
public class CartResponseDto {
	private Long cartId;
	
	private Long quantity;
	
	private Double totalPrice;
	
	private Date createDate;
	
	private Boolean selectedItems;
	
	private String productName;
	
	private ProductDetalisResponseDto productDetalis;
	
	public Double getDiscountedPrice() {
		return this.getProductDetalis().getDiscountedPrice();
	}
	
	public Double getTotalDiscountedPrice() {
		return this.getDiscountedPrice()*this.getQuantity();
	}
	
//	public String getProductName() {
//		return 
//	}
}
