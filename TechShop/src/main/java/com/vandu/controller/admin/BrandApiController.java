package com.vandu.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.server.MimeMappings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vandu.dto.BrandDto;
import com.vandu.exception.DuplicateDataException;
import com.vandu.model.Brand;
import com.vandu.model.Cart;
import com.vandu.model.Category;
import com.vandu.service.BrandService;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.ProductService;

import jakarta.servlet.annotation.MultipartConfig;

@RestController
@RequestMapping("/admin/brand")
@MultipartConfig
public class BrandApiController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductService productService;

	@Autowired
	private FileSystemStorageService fileSystemStorageService;

//	@PostMapping(value = "saveOrUpdate", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
//			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<BrandDto> saveOrUpdate(@ModelAttribute BrandDto brandDto) {
//		try {
//
//			Boolean check = true;
//			
//			if (brandDto.getBrandId() != null && !brandDto.getBrandId().equals("")) {
//				System.err.println("đã vào");
//				Brand currentBrand = brandService.findById(brandDto.getBrandId()).orElse(null);
//				
//				check = false;
//				
//				if (!currentBrand.getBrandCode().equalsIgnoreCase(brandDto.getBrandCode())) {
//					check = true;
//				}
//
//			}
//			
//			if(check) {
//				if(productService.checkDuplicateCode(brandDto.getBrandCode())) {
//					throw new DuplicateDataException("Mã định danh thương hiệu đã tồn tại");
//				}
//				
//			}
//
//			Brand brand = new Brand();
//
//			BeanUtils.copyProperties(brandDto, brand);
//
//			Category category = new Category();
//			category.setId(brandDto.getCategoryId());
//
//			brand.setCategory(category);
//
//			UUID uuid = UUID.randomUUID();
//
//			if (brandDto.getLogo() != null) {
//				String name = fileSystemStorageService.getStorageFileName(brandDto.getLogo(), uuid.toString());
//				fileSystemStorageService.saveFile(brandDto.getLogo(), name);
//				System.err.println("sdhdsgđsg");
//				brand.setLogo(name);
//			} else {
//				if (brandDto.getBrandId() != null) {
//					Brand currentBrand = brandService.findById(brandDto.getBrandId()).orElse(null);
//					if (currentBrand != null) {
//						brand.setLogo(currentBrand.getLogo());
//					}
//				}
//			}
//
//			Brand entity = brandService.save(brand);
//
//			BeanUtils.copyProperties(entity, brandDto);
//
//			return new ResponseEntity<>(brandDto, HttpStatus.OK);
//		} catch (DuplicateDataException e) {
//			throw e;
//		}
//	}
//


	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Long brandId) {
		Brand brand = brandService.findById(brandId).orElse(new Brand());

		if (brand != null) {
			brandService.delete(brand);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping("find/{id}")
	public ResponseEntity<BrandDto> findById(@PathVariable("id") Long brandId) {

		return ResponseEntity.ok(brandService.findByBrandDtoById(brandId));
	}

	@GetMapping("getAll")
	public ResponseEntity<List<Brand>> findById() {
//		Brand brand = brandService.findById(brandId).orElse(new Brand());

		return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
	}
	
	

	@GetMapping("pagination/{categoryId}")
	public ResponseEntity<Page<Brand>> pagination(@PathVariable("categoryId") Long id,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			@RequestParam("keyword") String keyword) {
		System.err.println("đã vào phân trang");

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<Brand> resultPage = keyword.equals("") ? brandService.findAllByCategory(id, pageable)
				: brandService.findAllByCategoryAndKeyword(id, keyword, pageable);

		return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}

}
