package com.vandu.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.service.DescriptionService;

@RestController
@RequestMapping("admin/description")
public class DescriptionApiController {
	
	@Autowired
	private DescriptionService descriptionService;
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		
		descriptionService.deleteById(id);
		
		return ResponseEntity.ok("Delete Success");
	}
}
