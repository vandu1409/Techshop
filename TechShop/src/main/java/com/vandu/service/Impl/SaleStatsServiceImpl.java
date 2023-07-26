package com.vandu.service.Impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandu.dto.response.StatisticalResponeDto;
import com.vandu.enums.OrderStatus;
import com.vandu.model.Order;
import com.vandu.model.Product;
import com.vandu.repository.OrderItemRepository;
import com.vandu.repository.OrderRepository;
import com.vandu.repository.ProductDetailsRepository;
import com.vandu.repository.ProductRepository;
import com.vandu.repository.UserRepository;
import com.vandu.service.SaleStatsService;

@Service
public class SaleStatsServiceImpl implements SaleStatsService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Long countOrderByStatus(OrderStatus orderStatus) {
		List<Order> list = orderRepository.findByStatus(orderStatus);

		return (long) list.size();
	}

	@Override
	public StatisticalResponeDto detailedStatistics() {

		return new StatisticalResponeDto().builder().pendingOrders(countOrderByStatus(OrderStatus.PENDING))
				.deliveredOrders(countOrderByStatus(OrderStatus.DELIVERED))
				.shippingOrders(countOrderByStatus(OrderStatus.SHIPING))
				.canceledOrder(countOrderByStatus(OrderStatus.CANELED)).toatlRevenue(totalrevenue())
				.lowQuantityProducts(productDetailsRepository.countLowQuantityProducts())
				.totalProduct(productRepository.count()).totalUser(userRepository.count()).build();

	}

	@Override
	public Double totalrevenue() {
		Double totalRevenue = 0.0;

		for (Order item : orderRepository.findByStatus(OrderStatus.DELIVERED)) {
			Double totalPriceOrderItem = item.getOrderItems().stream()
					.mapToDouble(orderItem -> orderItem.getInternalPrice() * orderItem.getQuantity()).sum();

			Double orderTotalRevenue = item.getTotalPrice() - totalPriceOrderItem;

			System.err.println(orderTotalRevenue);

			totalRevenue += orderTotalRevenue;
		}

		return totalRevenue;
	}

	@Override
	public List<Product> getBestSellingProducts(int number) {

		return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getTotalOrder).reversed()).limit(number)
				.collect(Collectors.toList());

	}

	@Override
	public List<StatisticalResponeDto> getOrderByYear(int year) {

		List<StatisticalResponeDto> list = new ArrayList<>();
		int month = 12;
		 year = 2023;
		for (int i = 1; i <= 12; i++) {
			StatisticalResponeDto statisticalResponeDto = new StatisticalResponeDto();
			statisticalResponeDto.builder().deliveredOrders(
					((long) orderRepository.findByStatusAndMonthAndYear(month, year, OrderStatus.DELIVERED).size()))
					.pendingOrders((long) orderRepository.findByStatusAndMonthAndYear(i, year, OrderStatus.PENDING).size())
					.shippingOrders((long) orderRepository.findByStatusAndMonthAndYear(i, year, OrderStatus.SHIPING).size())
					.canceledOrder((long) orderRepository.findByStatusAndMonthAndYear(i, year, OrderStatus.CANELED).size()).build();
			
			System.err.println(orderRepository.findByStatusAndMonthAndYear(i,year,OrderStatus.DELIVERED).size());
			list.add(statisticalResponeDto);
		}
		
		return list;

	}

}
