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

	@PostMapping("addOrder")
	public ResponseEntity<?> checkOut(@RequestBody CheckoutInfoDto checkoutInfoDto, HttpServletRequest request)
			throws VoucherNotApplicableException, UnsupportedEncodingException {
		try {
//			Address address = new Address();
			User user = UserHelper.getCurrentUser(request, userService);
			List<Cart> list = cartService.getSelectedCarts(user.getUserId(), true);

			Address address = addressService.getDefaultAddress(user.getUserId()).orElse(null);

			Order order = new Order();
			order.setCreateDate(new Date());
			order.setStatus(OrderStatus.PENDING);
			order.setNotes(checkoutInfoDto.getNotes());
			order.setUser(user);

			Double totalPrice = list.stream()
					.map(item -> item.getProductDetails().getDiscountedPrice() * item.getQuantity())
					.reduce(0.0, (a, b) -> a + b);

			order.setTotalPrice(totalPrice);
			order.setAddress(address);

			if (checkoutInfoDto.getVoucherId() != null) {

				VoucherApplicationResultDTO vocherResult = voucherService.applyVoucher(checkoutInfoDto.getVoucherId(),
						user.getUserId());

				if (vocherResult.getDiscountAmount() > 0) {
					Voucher voucher = new Voucher();
					voucher.setVoucherId(checkoutInfoDto.getVoucherId());
					order.setVoucher(voucher);
					order.setDiscount(vocherResult.getDiscountAmount());
					order.setTotalPrice(vocherResult.getDiscountedPrice());

				} else {
					throw new VoucherNotApplicableException("Voucher chưa được áp dụng vui lòng kiểm tra lại!");
				}

			}

			Order DBOrder = orderService.save(order);

			orderItemService.addProductsToOrder(list, DBOrder);

			cartService.deleteCartAll(user.getUserId(), true);

			if (checkoutInfoDto.getPaymentMethod() == 1) {
				Long amount = Math.round(DBOrder.getTotalPrice());

				PaymentResDTO paymentResDTO = orderService.payementByVnPay(amount,
						DBOrder.getOrderId(), request);

				Payments dBPayments = paymentsService.addToPayment(DBOrder, PaymentMethod.VNPAY, PaymentStatus.UNPAID);

				return ResponseEntity.ok(paymentResDTO);
			} else {
				Payments dBPayments = paymentsService.addToPayment(DBOrder, PaymentMethod.CASH, PaymentStatus.UNPAID);
			}

			return ResponseEntity.ok(DBOrder.getOrderId());
		} catch (VoucherNotApplicableException e) {
			throw e;
		}

	}
}
