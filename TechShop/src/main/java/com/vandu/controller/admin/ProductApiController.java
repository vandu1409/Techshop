package com.vandu.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

import com.vandu.dto.AttributesDto;
import com.vandu.dto.ProductDto;
import com.vandu.dto.response.CategoryResponseDto;
import com.vandu.dto.response.DiscountResponseDto;
import com.vandu.dto.response.ProductDetalisResponseDto;
import com.vandu.dto.response.ProductResponseDto;
import com.vandu.dto.response.ProductVersionResponseDto;
import com.vandu.exception.DuplicateDataException;
import com.vandu.exception.ProductNotFoundException;
import com.vandu.fillter.FillterProductsAdmin;
import com.vandu.model.Attributes;
import com.vandu.model.Brand;
import com.vandu.model.Category;
import com.vandu.model.CategoryAttributes;
import com.vandu.model.Product;
import com.vandu.model.ProductAttributes;
import com.vandu.model.ProductDetails;
import com.vandu.model.ProductImage;
import com.vandu.model.ProductSeries;
import com.vandu.model.ProductVersion;
import com.vandu.service.BrandService;
import com.vandu.service.DescriptionService;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.ProductAttributesService;
import com.vandu.service.ProductDetailsService;
import com.vandu.service.ProductImageService;
import com.vandu.service.ProductSeriesService;
import com.vandu.service.ProductService;
import com.vandu.service.ProductVersionService;

import jakarta.servlet.annotation.MultipartConfig;
import net.minidev.json.writer.BeansMapper.Bean;

@RestController
@RequestMapping("/admin/product")
@MultipartConfig
public class ProductApiController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductSeriesService productSeriesService;

	@Autowired
	private ProductDetailsService productDetailsService;

	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@Autowired
	private DescriptionService descriptionService;

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private BrandService brandService;

	@Autowired
	ProductAttributesService productAttributesService;

	@Autowired
	private ProductVersionService productVersionService;

	@PostMapping(value = "saveOrUpdate")
	public ResponseEntity<ProductDto> insert(@ModelAttribute ProductDto productDto) {

		try {
			
			checkDuplicateData(productDto);
			

			Product dbProduct = null;

			if (productDto.getProductId() != null) {
				dbProduct = productService.findById(productDto.getProductId()).orElse(null);
			}

			Product entity = productService.saveOrUpdate(productDto);

			BeanUtils.copyProperties(entity, productDto);

			productAttributesService.saveOrUpdateAttributes(dbProduct, productDto, entity);

			productImageService.saveImages(productDto.getListImages(), entity);

			if (productDto.getDescriptions() != null) {
				productDto.getDescriptions().stream().forEach(item -> {
					descriptionService.saveOrUpdate(item, entity);
				});

			}

			if (productDto.getVersions() != null) {
				productDto.getVersions().stream().forEach(item -> {
					ProductVersion productVersion = new ProductVersion();

					BeanUtils.copyProperties(item, productVersion);
					productVersion.setProduct(entity);

					ProductVersion dbProductVersion = productVersionService.save(productVersion);

					if (item.getProductDetails() != null) {
						item.getProductDetails().stream().forEach(details -> {
							ProductDetails productDetails = new ProductDetails();

							BeanUtils.copyProperties(details, productDetails);

							productDetailsService.saveOrUpdateProductDetalis(details, dbProductVersion);
						});
					}
				});

			}

			return new ResponseEntity<>(productDto, HttpStatus.CREATED);
		} catch (DuplicateDataException e) {
			throw e;
		}
	}

	private void checkDuplicateData(ProductDto productDto) {
		Boolean check = true;

		if (productDto.getProductId() != null && !productDto.getProductId().equals("")) {
			
			Product currentProduct = productService.findById(productDto.getProductId()).orElse(null);

			check = false;

			if (!currentProduct.getProductCode().equalsIgnoreCase(productDto.getProductCode())) {
				check = true;
			}

		}

		if (check) {
			if (productService.checkDuplicateCode(productDto.getProductCode())) {
				throw new DuplicateDataException("Mã định danh sản phẩm đã tồn tại");
			}

		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		Product product = productService.findById(id).orElse(new Product());

		if (product != null) {
			productService.delete(product);
		}

		return ResponseEntity.ok("Success");
	}

	@GetMapping("getAll")
	public ResponseEntity<Page<?>> getAll(@RequestParam("keyword") String keyword,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			@RequestParam("category") Optional<Long> categoryId) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		Long currentCategory = categoryId.orElse(null);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("createDate").descending());

		if (keyword.equals("")) {
			keyword = null;
		}

		if (currentCategory == 0 || currentCategory == null) {
			currentCategory = null;
		}

		Specification<Product> specification = FillterProductsAdmin.filterProducts(currentCategory, keyword);

		Page<Product> pageProduct = productService.findAll(specification, pageable);

		List<ProductResponseDto> productDtoList = pageProduct.getContent().stream().map(item -> {
			return productService.convertToProductResponseDto(item);
		}).toList();

		Page<ProductResponseDto> resultDtoPage = new PageImpl<>(productDtoList, pageProduct.getPageable(),
				pageProduct.getTotalElements());

		return ResponseEntity.ok(resultDtoPage);
	}

	@GetMapping("sell/{id}")
	public ResponseEntity<String> sell(@PathVariable("id") Long id) throws ProductNotFoundException {
		Product product = productService.findById(id).orElse(null);

		if (product == null) {
			throw new ProductNotFoundException("Không tìm thấy sản phẩm");
		}
		product.setAvailable(!product.isAvailable());
		productService.save(product);

		return ResponseEntity.ok("Success");
	}

	@GetMapping(value = { "getAttributesByBrand/{id}", "getAttributesByBrand/{id}/{productId}" })
	public ResponseEntity<?> getAttributesByBrand(@PathVariable("id") Long id,
			@PathVariable("productId") Optional<Long> productId) {
		Brand brand = brandService.findById(id).orElse(null);

		System.err.println(productId.orElse(null));
		List<AttributesDto> list = brand.getCategory().getCategoryAttributes().stream().map(item -> {
			AttributesDto attributesDto = new AttributesDto();
			BeanUtils.copyProperties(item.getAttributes(), attributesDto);

			if (productId.isPresent() && productId.get() != null) {
				System.err.println("đã vaia fisdfsfgshdvf");
				ProductAttributes productAttributes = productAttributesService
						.findByProductAndAttributes(productId.get(), item.getAttributes().getId());
				if (productAttributes != null) {
					attributesDto.setProductAttributesValue(productAttributes.getValue());
					System.err.println(productAttributes.getValue());
				}
			}

			return attributesDto;

		}).toList();

		return ResponseEntity.ok(list);
	}

	@GetMapping("find/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) throws ProductNotFoundException {
		System.err.println("đã vào");
		try {
			Product product = productService.findById(id).orElse(null);

			if (product == null) {
				throw new ProductNotFoundException("Không tìm thấy sản phẩm");
			}

			ProductResponseDto productResponseDto = productService.convertToProductResponseDto(product);

			System.err.println(product.getProductVersions());

			for (ProductVersion version : product.getProductVersions()) {
				System.err.println("Tên version product : " + version.getVersionName());
			}

//				productResponseDto.getVersions().stream().forEach(item->{
//					System.err.println("Tên version productDTO : "+item.getVersionName());
//				});
//			
			return ResponseEntity.ok(productResponseDto);
		} catch (ProductNotFoundException e) {
			throw e;

		}
	}
}
