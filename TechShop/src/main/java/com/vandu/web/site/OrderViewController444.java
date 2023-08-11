package com.vandu.web.site;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.vandu.dto.CheckoutInfoDto;
import com.vandu.enums.PaymentStatus;
import com.vandu.exception.OutOfStockException;
import com.vandu.exception.VoucherNotApplicableException;
import com.vandu.model.Order;
import com.vandu.model.Payments;
import com.vandu.service.CheckOutService;
import com.vandu.service.OrderService;
import com.vandu.service.PaymentsService;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderViewController444 {

	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentsService paymentsService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CheckOutService checkOutService;

	@GetMapping("order-details/{orderId}")
	public String orderDetails(@PathVariable("orderId") Long orderId, ModelMap modelMap) {
		modelMap.addAttribute("orderId", orderId);

		return "site/order-single";
	}

	@GetMapping("demo")
	public String demo(ModelMap modelMap) {
		modelMap.addAttribute("message",
				"Xin lỗi, thanh toán của bạn không thành công. Vui lòng kiểm tra lại thông tin thẻ và thử lại sau.");
		return "site/error";
	}

	@GetMapping("payment-notification")
	public String paymentNotification(@RequestParam("vnp_ResponseCode") Long responseCode,
			 @RequestParam("vnp_TransactionNo") String vnp_TransactionNo,
			ModelMap modelMap) throws UnsupportedEncodingException, VoucherNotApplicableException, OutOfStockException {

		if (responseCode == 00) {

			CheckoutInfoDto checkoutInfoDto = (CheckoutInfoDto) httpSession.getAttribute("checkoutInfoDto");
			checkoutInfoDto.setSaveOrderToDatabase(true);
			ResponseEntity<?> currentOrderId = checkOutService.checkOut(checkoutInfoDto, request, vnp_TransactionNo);
			httpSession.removeAttribute("checkoutInfoDto");
			
			return "redirect:/order-success/" + currentOrderId.getBody();
		} else {

//			orderService.deleteById(orderId);

			modelMap.addAttribute("message",
					"Xin lỗi, thanh toán của bạn không thành công. Vui lòng kiểm tra lại thông tin thẻ và thử lại sau.");
			return "site/error";
		}
	}
}
