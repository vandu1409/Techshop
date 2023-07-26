package com.vandu.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.MenuDto;
import com.vandu.model.Menu;
import com.vandu.service.MenuService;
import com.vandu.service.SubMenuService;

import jakarta.servlet.annotation.MultipartConfig;

@RestController
@RequestMapping("admin/menu")
@MultipartConfig
public class MenuApiController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private SubMenuService subMenuService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> saveOrUpdate(@ModelAttribute MenuDto menuDto) {

		Menu menu = menuService.saveOrUpdate(menuDto);

		if (menuDto.getSubMenus() != null && menu != null) {
			menuDto.getSubMenus().stream().forEach(item -> {
				subMenuService.saveOrUpdate(item, menu);
			});
		}

		return ResponseEntity.ok("Success");

	}
	
	@DeleteMapping("delete-sub-menu/{subMenuId}")
	public ResponseEntity<?> deleteSubMenu(@PathVariable("subMenuId") Long subMenuId){
		
		subMenuService.deleteById(subMenuId);
		
		return ResponseEntity.ok("delete Succes");
	}
	
	@DeleteMapping("delete-menu/{menuId}")
	public ResponseEntity<?> deleteMenu(@PathVariable("menuId")Long menuId){
		menuService.deleteById(menuId);
		
		return ResponseEntity.ok("Success");
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(@RequestParam("page") Optional<Integer> page,@RequestParam("size") Optional<Integer> size){
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize);
		
		Page<Menu> pageMenu = menuService.findAll(pageable);
		
		List<MenuDto> lisMenuDtos = pageMenu.getContent().stream().map(item->{
			return menuService.convertToMenuDto(item);
		}).toList();
		
		Page<MenuDto> pageResult = new PageImpl<>(lisMenuDtos, pageMenu.getPageable(), pageMenu.getTotalElements());
		
		return ResponseEntity.ok(pageResult);
	}
	
	@GetMapping("findMenu/{id}")
	public ResponseEntity<?> findMenu(@PathVariable("id") Long id){
		Menu menu = menuService.findById(id).orElse(null);
		
		MenuDto menuDto = menuService.convertToMenuDto(menu);
		
		return ResponseEntity.ok(menuDto);
	}

}
