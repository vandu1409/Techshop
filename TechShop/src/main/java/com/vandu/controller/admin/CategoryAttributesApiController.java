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

import com.vandu.dto.CategoryAttributesDto;
import com.vandu.exception.DuplicateDataException;
import com.vandu.model.Attributes;
import com.vandu.model.Category;
import com.vandu.model.CategoryAttributes;
import com.vandu.service.CategoryAttributesService;

@RestController
@RequestMapping("/admin/category-attributes")
public class CategoryAttributesApiController {

	@Autowired
	private CategoryAttributesService categoryAttributesService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> saveOrUpdate(@RequestBody CategoryAttributesDto categoryAttributesDto) {
		
		if(categoryAttributesService.findByCategoryAndAttributes(categoryAttributesDto.getCategoryId(), categoryAttributesDto.getAttributesId())!=null) {
			throw new DuplicateDataException("Thuộc tính đã tồn tại vui lòng kiểm tra lại!");
		}
		
		CategoryAttributes categoryAttributes = new CategoryAttributes();

		BeanUtils.copyProperties(categoryAttributesDto, categoryAttributes);

		Category category = new Category();
		category.setId(categoryAttributesDto.getCategoryId());

		Attributes attributes = new Attributes();
		attributes.setId(categoryAttributesDto.getAttributesId());

		categoryAttributes.setAttributes(attributes);
		categoryAttributes.setCategory(category);

		BeanUtils.copyProperties(categoryAttributesService.save(categoryAttributes), categoryAttributesDto);

		return ResponseEntity.ok(categoryAttributesDto);

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		categoryAttributesService.deleteById(id);

		return ResponseEntity.ok("success");
	}
	
	@GetMapping("edit/{id}")
	public ResponseEntity<?> edit(@PathVariable("id")Long id){
		CategoryAttributes categoryAttributes = categoryAttributesService.findById(id).orElse(null);
		
		CategoryAttributesDto categoryAttributesDto = new CategoryAttributesDto();
		
		BeanUtils.copyProperties(categoryAttributes, categoryAttributesDto);
		categoryAttributesDto.setAttributesId(categoryAttributes.getAttributes().getId());
		
		return ResponseEntity.ok(categoryAttributesDto);
	}
	
	@GetMapping("getAll/{categoryId}")
	public ResponseEntity<?> getAll(@PathVariable("categoryId") Optional<Long> categoryId,@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		Long currentCategoryId = categoryId.orElse(null);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<CategoryAttributes> resultPage = categoryAttributesService.findAllByCategory(currentCategoryId,pageable);

		return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}
	
//	@GetMapping("getAllAtributesByCategory/{categoryId}")
//	public ResponseEntity<?> getAllAtributesByCategory(@PathVariable("categoryId") Optional<Long> categoryId,@RequestParam("page") Optional<Integer> page,
//			@RequestParam("size") Optional<Integer> size) {
//		
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(3);
//		Long currentCategoryId = categoryId.orElse(null);
//
//		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
//
//		Page<CategoryAttributes> resultPage = categoryAttributesService.findAllAtrributesByCategory(currentCategoryId,pageable);
//
//		return new ResponseEntity<>(resultPage, HttpStatus.OK);
//	}
	
	

}
