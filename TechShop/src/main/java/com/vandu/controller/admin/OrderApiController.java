package com.vandu.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mchange.v2.codegen.bean.BeangenUtils;
import com.vandu.dto.response.AddressResponseDto;
import com.vandu.dto.response.OrderItemResponseDto;
import com.vandu.dto.response.OrderResponseDto;
import com.vandu.dto.response.PaymentsResponseDto;
import com.vandu.dto.response.UserResponseDto;
import com.vandu.dto.response.VoucherResponseDto;
import com.vandu.enums.OrderStatus;
import com.vandu.enums.PaymentStatus;
import com.vandu.fillter.FilterOrder;
import com.vandu.helper.UserHelper;
import com.vandu.model.Order;
import com.vandu.model.Payments;
import com.vandu.model.User;
import com.vandu.service.OrderService;
import com.vandu.service.PaymentsService;

import lombok.Delegate;

@RestController
@RequestMapping("admin/order")
public class OrderApiController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentsService paymentsService;

	@PutMapping("cancel/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Order order = orderService.findById(id).orElse(null);

		if (order != null && order.getStatus().equals(OrderStatus.PENDING)) {

			orderService.cancelOrder(order);
		}

		return ResponseEntity.ok("success");
	}

	@PutMapping("restore/{id}")
	public ResponseEntity<?> restore(@PathVariable("id") Long id) {
		Order order = orderService.findById(id).orElse(null);
		orderService.restoreOrder(order);

		return ResponseEntity.ok("success");
	}

	@PutMapping("{orderId}/updateStatus")
	public ResponseEntity<?> updateStatus(@PathVariable("orderId") Long orderId) {
		Order order = orderService.findById(orderId).orElse(null);

		if (order != null) {
			OrderStatus orderStatus = order.getStatus();

			orderStatus = orderStatus == OrderStatus.PENDING ? OrderStatus.SHIPING
					: orderStatus == OrderStatus.SHIPING ? OrderStatus.DELIVERED : orderStatus;
			order.setStatus(orderStatus);

			Order DBOrder = orderService.save(order);

			if (DBOrder.getStatus().equals(OrderStatus.DELIVERED)) {
				Payments payments = DBOrder.getPayments().get(0);
				payments.setPaymentStatus(PaymentStatus.PAID);
				DBOrder.setDeliveryDate(new Date());
				orderService.save(DBOrder);
				paymentsService.save(payments);
			}
		}

		return ResponseEntity.ok("success");
	}

	@GetMapping("getAll")
	public ResponseEntity<?> getAll(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("status") Optional<Integer> clientStatus,
			@RequestParam(name = "startDate", required = false)  Date startDate,
			@RequestParam(name = "endDate", required = false) Date endDate) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		int status = clientStatus.orElse(0);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		
		System.err.println(startDate);

		Specification<Order> specification = FilterOrder.filterOrder(status, startDate, endDate);

		List<OrderResponseDto> list = orderService.findAllOrdersWithSpecificationAndPageable(specification, pageable)
				.stream().map(item -> {

					return orderService.convertToOrderResponseDto(item);
				}).toList();

		System.err.println(
				orderService.findAllOrdersWithSpecificationAndPageable(specification, pageable).getTotalElements());

		Long totalElements = orderService.findAllOrdersWithSpecificationAndPageable(specification, pageable)
				.getTotalElements();

		System.err.println("totalPages : " + totalElements);
		System.err.println("dđ: " + orderService.findByStatus(status).size());
		Page<OrderResponseDto> listPage = PageableExecutionUtils.getPage(list, pageable, () -> totalElements);

		return ResponseEntity.ok(listPage);

	}
	
	@GetMapping("getOrder/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable("orderId") Long orderId){
		Order order = orderService.findById(orderId).orElse(null);
	
		
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		
		if(order!=null) {
		
				orderResponseDto = orderService.convertToOrderResponseDto(order);
			
		}
		
		return ResponseEntity.ok(orderResponseDto);
	}

}
