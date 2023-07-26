package com.vandu.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.model.Category;
import com.vandu.service.BrandService;
import com.vandu.service.CategoryService;

@Controller
@RequestMapping("admin/brand")
public class BrandViewController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("list/{categoryId}")
	public String add(@PathVariable("categoryId") Long categoryId, ModelMap modelMap) {

		Category category = categoryService.findById(categoryId).orElse(null);

		System.err.println(category.getCategoryCode());
		
		if (category != null) {
			modelMap.addAttribute("categoryId", categoryId);
			return "admin/brand";
		}
		
		
		return "404";  
	}

}
