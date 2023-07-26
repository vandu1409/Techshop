package com.vandu.web.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchViewController {

	@GetMapping("tim-kiem")
	public String search() {
		return "site/search";
	}
}
