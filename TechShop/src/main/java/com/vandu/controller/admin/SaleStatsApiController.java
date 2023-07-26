package com.vandu.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.response.ProductResponseDto;
import com.vandu.dto.response.StatisticalResponeDto;
import com.vandu.enums.OrderStatus;
import com.vandu.service.OrderService;
import com.vandu.service.ProductService;
import com.vandu.service.SaleStatsService;

@RestController
@RequestMapping("admin/saleStats")
public class SaleStatsApiController {

	@Autowired
	private SaleStatsService saleStatsService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;

	@GetMapping()
	public ResponseEntity<?> saleStats() {
		StatisticalResponeDto statisticalResponeDto = saleStatsService.detailedStatistics();
		System.err.println(statisticalResponeDto.toString());
		return ResponseEntity.ok(statisticalResponeDto);
	}

	@GetMapping("getListOrder")
	public ResponseEntity<?> getOrderStatistics() {
		List<StatisticalResponeDto> list = new ArrayList<>();

		for (int i = 0; i < 12; i++) {
			StatisticalResponeDto statis = new StatisticalResponeDto();
			statis.setDeliveredOrders((long) orderService.findByStatusAndMonth(i + 1, OrderStatus.DELIVERED).size());
			statis.setTotalOrders((long) orderService.findByMonth(i + 1).size());

			list.add(statis);
		}

		return ResponseEntity.ok(list);
	}

	@GetMapping("getOrderByYear/{year}")
	public ResponseEntity<?> getOrderByYear(@PathVariable("year") int year) {
		return ResponseEntity.ok(saleStatsService.getOrderByYear(year));
	}

	@GetMapping("getTopProduct/{number}")
	public ResponseEntity<?> getTopProduct(@PathVariable("number") int number) {
		List<ProductResponseDto> listProductResponseDtos = saleStatsService.getBestSellingProducts(number).stream().map(item->{
			return productService.convertToProductResponseDto(item);
		}).toList();
		
		
		return ResponseEntity.ok(listProductResponseDtos);
	}


}
