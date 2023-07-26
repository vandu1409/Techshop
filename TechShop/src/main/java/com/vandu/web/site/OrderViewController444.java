package com.vandu.web.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.vandu.enums.PaymentStatus;
import com.vandu.model.Order;
import com.vandu.model.Payments;
import com.vandu.service.OrderService;
import com.vandu.service.PaymentsService;

@Controller
public class OrderViewController444 {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentsService paymentsService;
	
	@GetMapping("order-details/{orderId}")
	public String orderDetails(@PathVariable("orderId")Long orderId,ModelMap modelMap) {
		modelMap.addAttribute("orderId",orderId);
		
		return "site/order-single";
	}
	
	@GetMapping("demo")
	public String demo(ModelMap modelMap) {
		modelMap.addAttribute("message","Xin lỗi, thanh toán của bạn không thành công. Vui lòng kiểm tra lại thông tin thẻ và thử lại sau.");
		return "site/error";
	}
	
	@GetMapping("payment-notification")
	public String paymentNotification(@RequestParam("vnp_ResponseCode") Long responseCode,@RequestParam("orderId")Long orderId,ModelMap modelMap) {
		
		if(responseCode == 00 && orderId !=null) {
			
			Payments payments = paymentsService.findByOrder(orderId).orElse(null);
			

			if(payments!=null) {
				payments.setPaymentStatus(PaymentStatus.PAID);
				paymentsService.save(payments);
			}

			return "redirect:/order-success/" + orderId;
		}else {
			
			orderService.deleteById(orderId);
			
			modelMap.addAttribute("message","Xin lỗi, thanh toán của bạn không thành công. Vui lòng kiểm tra lại thông tin thẻ và thử lại sau.");
			return "site/error";
		}
	}
}
