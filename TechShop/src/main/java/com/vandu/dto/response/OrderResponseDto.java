package com.vandu.dto.response;

import java.util.Date;
import java.util.List;

import com.vandu.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
	private Long orderId;

	private Date createDate;
	
	private Date deliveryDate;

	private Date updateDate;

	private Double discount;

	private Double totalPrice;

	private OrderStatus status;

	private String notes;

	private VoucherResponseDto voucher;

	private UserResponseDto user;

	private AddressResponseDto address;

	private List<OrderItemResponseDto> orderItems;

	private PaymentsResponseDto payments;

}
