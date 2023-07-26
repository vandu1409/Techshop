package com.vandu.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.AttributesDto;
import com.vandu.model.Attributes;
import com.vandu.model.Brand;
import com.vandu.service.AttributesService;

@RestController
@RequestMapping("admin/attributes")
public class AttributesApiController {
	
	
	@Autowired
	private AttributesService attributesService;
	
	@GetMapping("getAll")
	public ResponseEntity<List<Attributes>> getAll(){
		return ResponseEntity.ok(attributesService.findAll());
	}
	
	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> saveOrUpdate(@RequestBody AttributesDto attributesDto) {
		Attributes attributes = new Attributes();
		
		BeanUtils.copyProperties(attributesDto, attributes);
		
		BeanUtils.copyProperties(attributesService.save(attributes), attributesDto);
		
		return ResponseEntity.ok(attributesDto);
	}

	@GetMapping("pagination")
	public ResponseEntity<?> pagination(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
	
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<Attributes> list  = attributesService.findAll(pageable);
		

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("edit/{id}")
	public ResponseEntity<?> edit(@PathVariable("id") Long id){
		AttributesDto attributesDto = new AttributesDto();
		
		BeanUtils.copyProperties(attributesService.findById(id).get(), attributesDto);
		
		return ResponseEntity.ok(attributesDto);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		
		attributesService.deleteById(id);
		
		return ResponseEntity.ok("Success");
	}
}
