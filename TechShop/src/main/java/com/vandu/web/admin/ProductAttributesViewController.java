package com.vandu.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.dto.CategoryAttributesDto;
import com.vandu.service.ProductService;

@Controller
@RequestMapping("/admin/product-attributes")
public class ProductAttributesViewController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("list/{productId}")
	public String list(@PathVariable("productId")Long productId,ModelMap modelMap) {
		
		if(productService.findById(productId).get()!=null) {
			modelMap.addAttribute("productId",productId);
			return "admin/product-attributes";
		}
		
		return "404";
		
		
	}
}
