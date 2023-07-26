package com.vandu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatisticalResponeDto {

	private Long pendingOrders; // trường này là tổng đơn hàng chờ xác nhận
	
	private Long deliveredOrders; // đơn hàng đã giao
	
	private Long shippingOrders;
	
	private Long canceledOrder;
	
	private Long totalOrders;
	
	private Double toatlRevenue;// doanh thu
	
	private Long lowQuantityProducts;// sản phẩm sắp hết hàng
	
	private Long totalProduct;
	
	private Long totalUser;
	
	
	
	
}
