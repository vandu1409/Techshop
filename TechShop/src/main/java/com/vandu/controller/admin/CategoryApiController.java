package com.vandu.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.CategoryDto;
import com.vandu.dto.response.CategoryResponseDto;
import com.vandu.exception.DuplicateDataException;
import com.vandu.model.Brand;
import com.vandu.model.Category;
import com.vandu.service.BrandService;
import com.vandu.service.CategoryService;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.ProductSeriesService;
import com.vandu.service.ProductService;

import jakarta.servlet.annotation.MultipartConfig;

@RestController
@RequestMapping("admin/category")
@MultipartConfig
public class CategoryApiController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductSeriesService productSeriesService;

	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@Autowired
	private ProductService productService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> saveOrUpdate(@ModelAttribute CategoryDto categoryDto) {
		try {

			Category category = categoryService.saveOrUpdateCategory(categoryDto);

			categoryDto.getBrands().stream().forEach(item -> {

				Brand brand = brandService.saveOrUpdateBrand(item, category);

				item.getSeries().stream().forEach(series -> {
					productSeriesService.saveOrUpdateSeries(series, brand);
				});
			});

			return new ResponseEntity<>(categoryDto, HttpStatus.OK);
		} catch (DuplicateDataException e) {
			throw e;
		}

	}

	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(categoryService.findAll().stream().map(item -> {
			CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

			BeanUtils.copyProperties(item, categoryResponseDto);

			return categoryResponseDto;
		}).toList());
	}

	@GetMapping("pagination")
	public ResponseEntity<Page<?>> pagination(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("keyword") String keyword) {
//		System.err.println("đã vào phân trang");

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<Category> resultPage = keyword.equals("") ? categoryService.findAll(pageable)
				: categoryService.findByNameContaining(keyword, pageable);

		List<CategoryResponseDto> categoryDtoList = resultPage.getContent().stream().map(item -> {
			return categoryService.fromCategoryResponseDto(item);
		}).toList();

		Page<CategoryResponseDto> resultDtoPage = new PageImpl<>(categoryDtoList, resultPage.getPageable(),
				resultPage.getTotalElements());

		return new ResponseEntity<>(resultDtoPage, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Long categoryId) {
		categoryService.deleteById(categoryId);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping("find/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long categoryId) {

		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

		Category category = categoryService.findById(categoryId).orElse(null);

		if (category != null) {
			categoryResponseDto = categoryService.fromCategoryResponseDto(category);
		}

		return ResponseEntity.ok(categoryResponseDto);
	}

}
