package com.vandu.web.admin;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.service.UserService;

@Controller
@RequestMapping("admin/user")
public class UserViewController {
	
//	@Autowired
//	private RoleService roleService;
//	
//	@ModelAttribute("listRole")
//	public List<RoleDto> getAllRole(){
//		return roleService.findAll().stream().map(item ->{
//			RoleDto roleDto = new RoleDto();
//			
//			BeanUtils.copyProperties(item,roleDto);
//			
//			return roleDto;
//		}).toList();
//	}
	
	@GetMapping("list")
	public String list() {
		
		return "admin/user";
	}

}
