package com.vandu.service.Impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vandu.dto.CheckoutInfoDto;
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
import com.vandu.model.Payments;
import com.vandu.model.User;
import com.vandu.model.Voucher;
import com.vandu.service.AddressService;
import com.vandu.service.CartService;
import com.vandu.service.CheckOutService;
import com.vandu.service.OrderItemService;
import com.vandu.service.OrderService;
import com.vandu.service.PaymentsService;
import com.vandu.service.UserService;
import com.vandu.service.VoucherService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CheckoutServiceImpl implements CheckOutService {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private PaymentsService paymentsService;

	@Autowired
	private HttpSession session;

	@Override
	public ResponseEntity<?> checkOut(CheckoutInfoDto checkoutInfoDto, HttpServletRequest request,String vnp_TransactionNo)
			throws VoucherNotApplicableException, UnsupportedEncodingException, OutOfStockException {
		User user = UserHelper.getCurrentUser(request, userService);
		List<Cart> list = cartService.getSelectedCarts(user.getUserId(), true);

		
		    Optional<Cart> outOfStockItem = list.stream()
                    .filter(item -> item.isOutOfStock())
                    .findFirst();
		    
		if(outOfStockItem.isPresent()) {
			throw new OutOfStockException("Sản phẩm "+outOfStockItem.get().getProductName() +" đã hết hàng vui lòng kiểm tra lại!");
		}
			
		

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

		if (checkoutInfoDto.getPaymentMethod() == 1 && !checkoutInfoDto.isSaveOrderToDatabase()) {
			Long amount = Math.round(order.getTotalPrice());

			PaymentResDTO paymentResDTO = orderService.payementByVnPay(amount, order.getOrderId(), request);

			session.setAttribute("checkoutInfoDto", checkoutInfoDto); // vì vnpay không thể thanh toán 2 tab trên 1
																		// trình duyệt nên lưu trên sesion là tối ưu
																		// nhất

			return ResponseEntity.ok(paymentResDTO);
		}

		Order DBOrder = orderService.save(order);

		orderItemService.addProductsToOrder(list, DBOrder);

		cartService.deleteCartAll(user.getUserId(), true);

		if (checkoutInfoDto.getPaymentMethod() == 1) {
			Long amount = Math.round(DBOrder.getTotalPrice());

			PaymentResDTO paymentResDTO = orderService.payementByVnPay(amount, DBOrder.getOrderId(), request);

			Payments dBPayments = paymentsService.addToPayment(DBOrder, PaymentMethod.VNPAY, PaymentStatus.PAID,vnp_TransactionNo);

//			return ResponseEntity.ok(paymentResDTO);
		} else {
			Payments dBPayments = paymentsService.addToPayment(DBOrder, PaymentMethod.CASH, PaymentStatus.UNPAID,null);
		}

		return ResponseEntity.ok(DBOrder.getOrderId());
	}

	public boolean isOutOfStock(List<Cart> list) {

		return list.stream().anyMatch(item -> item.isOutOfStock());
	}

}
