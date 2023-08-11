package com.vandu.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/message")
public class MessageViewController {
	
	@GetMapping("list")
	public String list() {
		return "admin/message";
	}
}
