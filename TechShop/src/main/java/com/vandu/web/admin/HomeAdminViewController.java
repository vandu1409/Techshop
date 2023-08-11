package com.vandu.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAdminViewController {

	@GetMapping("admin/home")
	public String home() {
		return "admin/home";
	}
	
	@GetMapping("admin/product-success")
	public String success() {
		return "admin/success";
	}
	
	@GetMapping("admin/category-success")
	public String categorySuccess() {
		return "admin/category-success";
	}
	@GetMapping("admin/menu-success")
	public String menuSuccess() {
		return "admin/menu-success";
	}
}
