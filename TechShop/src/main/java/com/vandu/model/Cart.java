package com.vandu.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vandu.helper.Views;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(Views.Public.class)
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	private Long quantity;
	
//	private Double totalPrice;
	
	private Date createDate;
	
	private Boolean selectedItems;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "productDetailsId")
	private ProductDetails productDetails;
	
	public Double getTotalPrice() {
		return this.getDiscountedPrice()*this.getQuantity();
	}
	
	public Double getDiscountedPrice() {
		return this.getProductDetails().getDiscountedPrice();
	}
	
	public Double getTotalDiscountedPrice() {
		return this.getDiscountedPrice()*this.getQuantity();
	}
	
	public boolean isOutOfStock() {
		return this.getProductDetails().getQuantity() - this.getQuantity() < 0 ;
	}

	public String getProductName() {
		return this.getProductDetails().getProductVersion().getProduct().getName() + " - "
				+ this.getProductDetails().getProductVersion().getVersionName() + " - Màu : "
				+ this.getProductDetails().getColor().getName();
	}
}
