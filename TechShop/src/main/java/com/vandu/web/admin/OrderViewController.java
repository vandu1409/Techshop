package com.vandu.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/order")
public class OrderViewController {

	
	@GetMapping("list")
	public String list() {
		return "admin/orderList";
	}
	
	@GetMapping("details/{orderId}")
	public String orderDetails(@PathVariable("orderId")Long orderId,ModelMap modelMap) {
		modelMap.addAttribute("orderId",orderId);
		
		return "admin/order-single";
	}
}
