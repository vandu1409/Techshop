package com.vandu.web.site;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.vandu.dto.response.AddressResponseDto;
import com.vandu.model.Brand;
import com.vandu.model.Category;
import com.vandu.model.Order;
import com.vandu.service.BrandService;
import com.vandu.service.CategoryService;
import com.vandu.service.OrderService;

@Controller
public class CheckoutViewController {
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("listCategory")
	public List<Category> getAllCategory(){
		return categoryService.findAll();
	}
	
	@ModelAttribute("listBrand")
	public List<Brand> getAllBrand() {
		return brandService.findAll();
	}
	
	@GetMapping("thanh-toan")
	public String checkOut() {
		return "/site/checkout";
	}
	@GetMapping("order-success/{orderId}")
	public String orderSuccess(@PathVariable("orderId")Long orderId,ModelMap modelMap) {
		
		Order order = orderService.findById(orderId).orElse(null);
		
		if(order!=null) {
			AddressResponseDto addressResponseDto = new AddressResponseDto();
			BeanUtils.copyProperties(order.getAddress(), addressResponseDto);
			modelMap.addAttribute("address",addressResponseDto);
			modelMap.addAttribute("orderId",orderId);
		}else {
			AddressResponseDto addressResponseDto = new AddressResponseDto();
			modelMap.addAttribute("address",addressResponseDto);
		}
//		
		
		return "site/order-success";
	}
}
