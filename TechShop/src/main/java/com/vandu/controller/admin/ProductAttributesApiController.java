package com.vandu.controller.admin;

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

import com.vandu.dto.ProductAttributesDto;
import com.vandu.exception.DuplicateDataException;
import com.vandu.model.Attributes;
import com.vandu.model.Brand;
import com.vandu.model.Product;
import com.vandu.model.ProductAttributes;
import com.vandu.model.CategoryAttributes;
import com.vandu.service.ProductAttributesService;

@RestController
@RequestMapping("admin/product-attributes")
public class ProductAttributesApiController {
	
	@Autowired
	private ProductAttributesService productAttributesService;

	
	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> saveOrUpdate(@RequestBody ProductAttributesDto productAttributesDto){
		try {
			ProductAttributes productAttributes = new ProductAttributes();
			
			boolean checkDuplicate = true;
			
			if(productAttributesDto.getId()!=null) {
				ProductAttributes dbProductAttributes = productAttributesService.findById(productAttributesDto.getId()).orElse(null);
				
				if(dbProductAttributes.getAttributes().getId()== productAttributesDto.getAttributesId()) {
					checkDuplicate = false;
				}
				
			}
			
			if(checkDuplicate) {
				ProductAttributes dbProductAttributes = productAttributesService.findByProductAndAttributes(productAttributesDto.getProductId(), productAttributesDto.getAttributesId());
				
				if(dbProductAttributes!=null) {
					throw new DuplicateDataException("Thuộc tính này đã tồn tại!");
				}
			}
			
			BeanUtils.copyProperties(productAttributesDto, productAttributes);
			
			Product product = new Product();
			product.setProductId(productAttributesDto.getProductId());
			
			Attributes attributes = new Attributes();
			attributes.setId(productAttributesDto.getAttributesId());
			
			productAttributes.setAttributes(attributes);
			productAttributes.setProduct(product);
			
			BeanUtils.copyProperties(productAttributesService.save(productAttributes), productAttributesDto);
			
			return ResponseEntity.ok(productAttributesDto);
		} catch (DuplicateDataException e) {
			throw e;
		}
	}
	
	
	@GetMapping("getAll/{productId}")
	public ResponseEntity<Page<ProductAttributes>> getAll(@PathVariable("productId") Optional<Long> productId,@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		Long currentProductId = productId.orElse(null);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<ProductAttributes> resultPage = productAttributesService.findAllByProduct(currentProductId,pageable);
		
for (ProductAttributes productAttributes : resultPage) {
	System.err.println(productAttributes.getAttributes().getName());
	
}
		return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}
	
	@GetMapping("edit/{id}")
	public ResponseEntity<?> edit(@PathVariable("id")Long id){
		ProductAttributesDto productAttributesDto = new ProductAttributesDto();
		ProductAttributes productAttributes = productAttributesService.findById(id).orElse(null);
		
		BeanUtils.copyProperties(productAttributes, productAttributesDto);
		productAttributesDto.setAttributesId(productAttributes.getAttributes().getId());
		
		
		return ResponseEntity.ok(productAttributesDto);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")Long id){
		productAttributesService.deleteById(id);
		
		return ResponseEntity.ok("success");
	}
}
