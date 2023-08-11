package com.vandu.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.dto.response.CategoryResponseDto;
import com.vandu.model.Category;
import com.vandu.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class CategoryViewController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("saveOrUpdate")
	public String saveOrUpdate(ModelMap modelMap) {
		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
		modelMap.addAttribute("category",categoryResponseDto);
		
		return "admin/addCategory";
	}
	
	@GetMapping("list")
	public String list() {
		return "admin/categories";
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id")Long id,ModelMap modelMap) {
		Category category = categoryService.findById(id).orElse(null);
		
		CategoryResponseDto categoryResponseDto = categoryService.fromCategoryResponseDto(category);
		
		modelMap.addAttribute("category",categoryResponseDto);
		
		return "admin/addCategory";
		
	}

	
}
