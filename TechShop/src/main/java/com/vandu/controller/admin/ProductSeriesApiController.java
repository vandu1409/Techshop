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

import com.vandu.dto.BrandDto;
import com.vandu.dto.ProductSeriesDto;
import com.vandu.exception.DuplicateDataException;
import com.vandu.model.Brand;
import com.vandu.model.ProductSeries;
import com.vandu.service.ProductSeriesService;
import com.vandu.service.ProductService;

@RestController
@RequestMapping("/admin/product-series-management")
public class ProductSeriesApiController {

	@Autowired
	private ProductSeriesService productSeriesService;

	@Autowired
	private ProductService productService;

	@PostMapping("insert")
	public ResponseEntity<ProductSeriesDto> insert(@RequestBody ProductSeriesDto productSeriesDto) {

		try {

			Boolean check = true;

			if (productSeriesDto.getProductSeriesId() != null && !productSeriesDto.getProductSeriesId().equals("")) {
				System.err.println("đã vào");
				ProductSeries currentProductSeries = productSeriesService
						.findById(productSeriesDto.getProductSeriesId()).orElse(null);

				check = false;

				if (!currentProductSeries.getSeriesCode().equalsIgnoreCase(productSeriesDto.getSeriesCode())) {
					check = true;
				}

			}

			if (check) {
				if (productService.checkDuplicateCode(productSeriesDto.getSeriesCode())) {
					throw new DuplicateDataException("Mã định danh thương hiệu đã tồn tại");
				}

			}

			ProductSeries productSeries = new ProductSeries();
			BeanUtils.copyProperties(productSeriesDto, productSeries);

			Brand brand = new Brand();
			brand.setBrandId(productSeriesDto.getBrandId());
			productSeries.setBrand(brand);

			var entities = productSeriesService.save(productSeries);

//		BeanUtils.copyProperties(entities, productSeriesDto);

			return new ResponseEntity<>(productSeriesDto, HttpStatus.CREATED);
		} catch (DuplicateDataException e) {
			throw e;
		}
	}

	

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		ProductSeries productSeries = productSeriesService.findById(id).orElse(new ProductSeries());

		if (productSeries != null) {
			productSeriesService.delete(productSeries);
		}

		return ResponseEntity.ok("success");
	}

	@GetMapping("find/{id}")
	public ResponseEntity<ProductSeries> findById(@PathVariable("id") Long id) {
		ProductSeries productSeries = productSeriesService.findById(id).orElse(new ProductSeries());

		return ResponseEntity.ok(productSeries);
	}

	@GetMapping("findByBrand/{brandId}")
	public ResponseEntity<List<ProductSeriesDto>> findByBrand(@PathVariable("brandId") Long brandId) {
//		return productSeriesService.findBy

		return ResponseEntity.ok(productSeriesService.findAllByBrand(brandId).stream().map(item -> {
			ProductSeriesDto productSeriesDto = new ProductSeriesDto();

			BeanUtils.copyProperties(item, productSeriesDto);
			System.err.println(productSeriesDto.getName());

			return productSeriesDto;
		}).toList());
	}

	@GetMapping("getAll/{brandId}")
	public ResponseEntity<Page<ProductSeries>> pagination(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("keyword") String keyword,
			@PathVariable("brandId") Long brandId) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<ProductSeries> resultPage = keyword.equals("") ? productSeriesService.findAllByBrand(brandId, pageable)
				: productSeriesService.findAllByBrand(brandId, keyword, pageable);

		int totalPages = resultPage.getTotalPages();
		System.err.println(totalPages);

		for (ProductSeries productSeries : resultPage) {
			System.err.println(productSeries.getBrand().getName());
		}

		return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}
}
