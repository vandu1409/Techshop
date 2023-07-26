package com.vandu.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.dto.MenuDto;
import com.vandu.model.Menu;
import com.vandu.service.MenuService;

@Controller
@RequestMapping("admin/menu")
public class MenuViewController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("save")
	public String save(ModelMap modelMap) {
		MenuDto menuDto  =  new MenuDto();
		
		modelMap.addAttribute("menu",menuDto);
		
		return "admin/addMenu";
	}
	
	@GetMapping("list")
	public String list() {
		return "admin/list-menu";
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id,ModelMap modelMap) {
		Menu menu = menuService.findById(id).orElse(null);
		
		MenuDto menuDto = menuService.convertToMenuDto(menu);
		
		modelMap.addAttribute("menu",menuDto);
		
		return "admin/addMenu";
		
	}
	
}
