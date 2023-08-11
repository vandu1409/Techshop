package com.vandu.controller.site;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.AddressDto;
import com.vandu.dto.CheckoutInfoDto;
import com.vandu.dto.EmailDto;
import com.vandu.dto.PaymentResDTO;
import com.vandu.dto.VoucherApplicationResultDTO;
import com.vandu.enums.OrderStatus;
import com.vandu.enums.PaymentMethod;
import com.vandu.enums.PaymentStatus;
import com.vandu.exception.OutOfStockException;
import com.vandu.exception.VoucherNotApplicableException;
import com.vandu.helper.UserHelper;
import com.vandu.model.Address;
import com.vandu.model.Cart;
import com.vandu.model.Order;
import com.vandu.model.OrderItem;
import com.vandu.model.Payments;
import com.vandu.model.User;
import com.vandu.model.Voucher;
import com.vandu.service.AddressService;
import com.vandu.service.CartService;
import com.vandu.service.CheckOutService;
import com.vandu.service.EmailService;
import com.vandu.service.OrderItemService;
import com.vandu.service.OrderService;
import com.vandu.service.PaymentsService;
import com.vandu.service.UserService;
import com.vandu.service.VoucherService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CheckOutApiController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private PaymentsService paymentsService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private CheckOutService checkOutService;

	@PostMapping("addOrder")
	public ResponseEntity<?> checkOut(@RequestBody CheckoutInfoDto checkoutInfoDto, HttpServletRequest request)
			throws VoucherNotApplicableException, UnsupportedEncodingException, OutOfStockException {
		try {

			return checkOutService.checkOut(checkoutInfoDto, request, null);

		} catch (VoucherNotApplicableException e) {
			throw e;

		} catch (OutOfStockException e) {
			throw e;
		}
	}

}
