package com.vandu.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/role")
public class RoleViewController {
	
	@GetMapping("list")
	public String list() {
		return "admin/role";
	}

}
