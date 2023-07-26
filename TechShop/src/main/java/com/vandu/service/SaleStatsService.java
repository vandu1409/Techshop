package com.vandu.service;

import java.util.List;

import com.vandu.dto.response.StatisticalResponeDto;
import com.vandu.enums.OrderStatus;
import com.vandu.model.Product;

public interface SaleStatsService {

	Double totalrevenue();

	StatisticalResponeDto detailedStatistics();

	Long countOrderByStatus(OrderStatus orderStatus);

	

	List<Product> getBestSellingProducts(int number);

	List<StatisticalResponeDto> getOrderByYear(int year);



}
