package com.vandu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;
	
	private Double totalPrice;
	
	private Long quantity;
	
	private Double internalPrice; // dùng để lưu giá nhập hiện tại của đơn hàng vì giá nhập có thể thay đổi theo thời gian
	
	private Boolean feedBack;
	
	@ManyToOne
	@JoinColumn(name = "productDetailsId")
	private ProductDetails productDetails;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	@JsonIgnore
	private Order order;
}
