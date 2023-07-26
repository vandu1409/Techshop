package com.vandu.controller.site;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.response.OrderResponseDto;
import com.vandu.enums.OrderStatus;
import com.vandu.fillter.FilterOrder;
import com.vandu.fillter.FilterOrderByUser;
import com.vandu.helper.UserHelper;
import com.vandu.model.Order;
import com.vandu.model.ProductDetails;
import com.vandu.model.User;
import com.vandu.service.OrderService;
import com.vandu.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("order")
public class OrderSiteApiController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("getAllByUser")
	public ResponseEntity<?> getAllByUser(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("status") Optional<Integer> clientStatus){
		
		User user = UserHelper.getCurrentUser(request, userService);
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		int status = clientStatus.orElse(0);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize,Sort.by("createDate").descending());

		Specification<Order> specification = FilterOrderByUser.filterOrder(status, user.getUserId());

		List<OrderResponseDto> list = orderService.findAllOrdersWithSpecificationAndPageable(specification, pageable)
				.stream().map(item -> {

					return orderService.convertToOrderResponseDto(item);
				}).toList();

		

		Long totalElements = orderService.findAllOrdersWithSpecificationAndPageable(specification, pageable)
				.getTotalElements();

		
		Page<OrderResponseDto> listPage = PageableExecutionUtils.getPage(list, pageable, () -> totalElements);

		return ResponseEntity.ok(listPage);
	}
	
	@PutMapping("cancelOrder/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Order order = orderService.findById(id).orElse(null);

		if (order != null && order.getStatus().equals(OrderStatus.PENDING)) {

			orderService.cancelOrder(order);
		}

		return ResponseEntity.ok("success");
	}
	
	@GetMapping("getOrder/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable("orderId") Long orderId){
		Order order = orderService.findById(orderId).orElse(null);
		User user = UserHelper.getCurrentUser(request, userService);
		
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		
		if(order!=null) {
			if(order.getUser().getUserId() == user.getUserId()) {
				orderResponseDto = orderService.convertToOrderResponseDto(order);
			}
		}
		
		return ResponseEntity.ok(orderResponseDto);
	}
}
