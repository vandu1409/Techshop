package com.vandu.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.vandu.dto.ProductVersionDto;
import com.vandu.model.Product;
import com.vandu.model.ProductVersion;
import com.vandu.repository.ProductVersionRepository;
import com.vandu.service.ProductVersionService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("admin/product-version-management")
public class ProductVersionApiController {

	@Autowired
	private ProductVersionService productVersionService;
	
	@Autowired ProductVersionRepository repository;

	@PostMapping(value = "saveOrUpdate", consumes = { MediaType.APPLICATION_JSON_VALUE, })
	public ResponseEntity<ProductVersionDto> saveOrUpdate(@RequestBody ProductVersionDto productVersionDto) {
		ProductVersion productVersion = new ProductVersion();

		BeanUtils.copyProperties(productVersionDto, productVersion);

		Product product = new Product();
		product.setProductId(productVersionDto.getProductId());

		productVersion.setProduct(product);

		ProductVersion entity = productVersionService.save(productVersion);

		BeanUtils.copyProperties(entity, productVersionDto);

		return new ResponseEntity<>(productVersionDto, HttpStatus.OK);

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		ProductVersion productVersion = productVersionService.findById(id).orElse(new ProductVersion());

		if (productVersion != null) {
			productVersionService.delete(productVersion);
		}

		return ResponseEntity.ok("Success");
	}
	
	@GetMapping("find/{id}")
	public ResponseEntity<ProductVersion> findById(@PathVariable("id") Long id){
		
		return ResponseEntity.ok(productVersionService.findById(id).get());
	}

	@GetMapping("getAll/{productId}")
	public ResponseEntity<Page<ProductVersion>> getAll(@PathVariable("productId") Long productId,
			@RequestParam("page")Optional<Integer> page,@RequestParam("size") Optional<Integer> size,@RequestParam("keyword")Optional<String> keyword) {


		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		String search = keyword.orElse(null);

		System.err.println("page " + page + "size " + size + "key word ");

		Pageable pageable = PageRequest.of(currentPage-1, pageSize);

		Page<ProductVersion> list =keyword.equals("") ? productVersionService.findByProduct(productId, pageable)
				: productVersionService.findByProduct(productId, search,pageable);
	
		
	

		return ResponseEntity.ok(list);

	}

}
