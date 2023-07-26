package com.vandu.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.model.Product;
import com.vandu.model.ProductSeries;
import com.vandu.service.ProductService;

@Controller
@RequestMapping("admin/product-version-management")
public class ProductVersionViewController {
	

	@Autowired
	private ProductService productService;
	
	@GetMapping("list/{productId}")
	public String list(@PathVariable("productId")Long productId,ModelMap modelMap) {
		Product product = productService.findById(productId).orElse(null);
		
		if(product==null) {
			return "404";
		}
		
		modelMap.addAttribute("productId",productId);
		return "admin/productVersion";
	}
}
