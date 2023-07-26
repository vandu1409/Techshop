package com.vandu.web.admin;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.dto.ColorDto;
import com.vandu.model.ProductVersion;
import com.vandu.service.ColorService;
import com.vandu.service.ProductVersionService;

@Controller
@RequestMapping("admin/product-details-management")
public class ProductDetailsViewController {
	
	@Autowired
	ProductVersionService productVersionService ;
	
	@Autowired
	ColorService colorService;
	
	@ModelAttribute("listColor")
	public List<ColorDto> listColor(){
		return colorService.findAll().stream().map(item ->{
			ColorDto colorDto = new ColorDto();
			
			BeanUtils.copyProperties(item, colorDto);
			
			return colorDto;
		}).toList();
	}
	
	@GetMapping("list/{versionId}")
	public String list(@PathVariable("versionId")Long id,ModelMap model) {
		
		ProductVersion productVersion = productVersionService.findById(id).get();
		
		if(productVersion==null) {
			return "404";
		}
		
		model.addAttribute("productVersion",productVersion);
		
		return "admin/productDetails";
		
	}

}
