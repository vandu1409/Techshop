package com.vandu.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/product-series-management")
public class ProductSeriesViewController {

	@GetMapping("list/{brandId}")
	public String list(ModelMap modelMap,@PathVariable("brandId") Long id) {
		modelMap.addAttribute("brandId",id);
		return "/admin/series";
	}
}
