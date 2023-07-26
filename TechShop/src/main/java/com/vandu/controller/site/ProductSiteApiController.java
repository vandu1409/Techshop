package com.vandu.controller.site;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.vandu.dto.BrandDto;
import com.vandu.dto.response.FeedBackResponseDto;
import com.vandu.dto.response.ProductDetalisResponseDto;
import com.vandu.dto.response.ProductResponseDto;
import com.vandu.dto.response.ProductSeriesResponseDto;
import com.vandu.dto.response.ProductVersionResponseDto;
import com.vandu.fillter.FilterProducts;
import com.vandu.helper.Views;
import com.vandu.model.Brand;
import com.vandu.model.Color;
import com.vandu.model.FeedBack;
import com.vandu.model.FeedBackImage;
import com.vandu.model.Product;
import com.vandu.model.ProductDetails;
import com.vandu.model.ProductSeries;
import com.vandu.model.ProductVersion;
import com.vandu.repository.ProductDetailsRepository;
import com.vandu.service.BrandService;
import com.vandu.service.FeedBackService;
import com.vandu.service.ProductDetailsService;
import com.vandu.service.ProductSeriesService;
import com.vandu.service.ProductService;
import com.vandu.service.ProductVersionService;

import net.minidev.json.writer.BeansMapper.Bean;

@RestController()
@RequestMapping("dtdd")
public class ProductSiteApiController {
	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductSeriesService productSeriesService;

	@Autowired
	private ProductDetailsService productDetailsService;

	@Autowired
	private ProductVersionService productVersionService;
	
	@Autowired
	private FeedBackService feedBackService;

	@GetMapping("getAll")
	public ResponseEntity<Page<?>> getAll(@RequestParam("page") Optional<Integer> page,
			@RequestParam("brandCode") Optional<String> brandCodeList,
			@RequestParam("seriesCode") Optional<String> sCode, @RequestParam("categoryCode") Optional<String> cCode,
			@RequestParam("sort") Optional<Integer> clientSortOrder,
			@RequestParam(value = "minPrice", required = false) String min,
			@RequestParam(value = "maxPrice", required = false) String max,
			@RequestParam("featureValues")Optional<String> featureValues) {
		int pageSize = page.orElse(1);
		String seriesCode = sCode.orElseGet(() -> null);
		String categoryCode = cCode.orElse(null);

		if (seriesCode.equals("")) {
			seriesCode = null;
		}

		int sort = clientSortOrder.orElse(1);
		Double minPrice = null;
		Double maxPrice = null;

		System.err.println(seriesCode + "serr");

		if (min != null && !min.equals("null")) {
			minPrice = Double.parseDouble(min);
		}

		if (max != null && !max.equals("null")) {
			maxPrice = Double.parseDouble(max);
		}

		String brandCodes = brandCodeList.orElse(null);
		String listFeatureValues = featureValues.orElse(null);

		Sort sortObj = null;
		if (sort == 1) {
			sortObj = Sort.by("price").ascending();
		} else if (sort == 2) {
			sortObj = Sort.by("price").descending();

		} else if (sort == 3) {
			sortObj = Sort.by("name").ascending();

		} else if (sort == 4) {
			sortObj = Sort.by("name").descending();

		} else if (sort == 5) {
			sortObj = Sort.by("createDate").ascending();

		} else if (sort == 6) {
			sortObj = Sort.by("createDate").descending();

		} else if (sort == 0) {
			sortObj = Sort.by("price").ascending();
		}

		Pageable pageable = PageRequest.of(0, pageSize * 8, sortObj);
		Specification<Product> spec = FilterProducts.filterProducts(categoryCode, brandCodes, seriesCode, minPrice,
				maxPrice,listFeatureValues);

		Page<Product> resultPage = productService.findAll(spec, pageable);

		List<ProductResponseDto> listProResponseDto = resultPage.getContent().stream().map(item -> {
			return productService.convertToProductResponseDto(item);
		}).toList();

		Page<ProductResponseDto> resultDtoPage = new PageImpl(listProResponseDto, resultPage.getPageable(),
				resultPage.getTotalElements());

		return ResponseEntity.ok(resultDtoPage);

	}

	@GetMapping("getAllBrand")
//	@JsonView(Views.Public.class)
	public ResponseEntity<List<Brand>> getAllBrand() {
		return ResponseEntity.ok(brandService.findAll());
	}

	@GetMapping("getAllByCategory/{categoryCode}")
	public ResponseEntity<List<BrandDto>> getAllByCategory(@PathVariable("categoryCode") String categoryCode) {
		return ResponseEntity.ok(brandService.findAllByCategoryCode(categoryCode).stream().map(item -> {
			BrandDto brandDto = new BrandDto();

			BeanUtils.copyProperties(item, brandDto);

			return brandDto;
		}).toList());
	}

	@GetMapping("getSeries/{brandCode}")
	public ResponseEntity<?> getAllSeries(@PathVariable("brandCode") String brandCode) {
		List<ProductSeries> productSeries = productSeriesService.findAllByBrandCode(brandCode);

		List<ProductSeriesResponseDto> lisProductSeriesResponseDtos = productSeries.stream().map(item -> {
			ProductSeriesResponseDto seriesResponseDto = new ProductSeriesResponseDto();

			BeanUtils.copyProperties(item, seriesResponseDto);

			return seriesResponseDto;
		}).toList();

		return ResponseEntity.ok(lisProductSeriesResponseDtos);
	}

	@GetMapping("getColorByVersion/{versionId}")
	public ResponseEntity<List<Color>> getColorByVersion(@PathVariable("versionId") Long versionId) {

		return ResponseEntity.ok(productDetailsService.findColorByVersion(versionId));
	}

	@GetMapping("getVersionByProduct/{productId}")
	public ResponseEntity<List<ProductVersionResponseDto>> getVersionByProduct(@PathVariable("productId") Long id) {
		Product product = productService.findById(id).orElse(null);

		List<ProductVersionResponseDto> list = product.getProductVersions().stream().map(item->{
			ProductVersionResponseDto productVersionResponseDto = new ProductVersionResponseDto();
			
			BeanUtils.copyProperties(item, productVersionResponseDto);
			
			return productVersionResponseDto;
		}).toList();

		return ResponseEntity.ok(list);

	}

	@GetMapping("getProductDetailsByVersion/{versionId}")
//	@JsonView(Views.Public.class)
	public ResponseEntity<?> getProductDetailsByVersion(@PathVariable("versionId") Long versionId) {

		System.err.println(versionId);

		ProductVersion productVersion = productVersionService.findById(versionId).orElse(null);

		for (ProductDetails v : productVersion.getProductDetails()) {
			System.err.println(v.getImage() + " đay là hinh anh");
		}
		
		List<ProductDetalisResponseDto> list = new ArrayList<>();

//		if (productVersion != null) {
			list = productVersion.getProductDetails().stream().map(item->{
				ProductDetalisResponseDto productDetalisResponseDto = new ProductDetalisResponseDto();
				
				BeanUtils.copyProperties(item, productDetalisResponseDto);
				productDetalisResponseDto.setColorName(item.getColor().getName());
				productDetalisResponseDto.setImportPrice(null);
				
				return productDetalisResponseDto;
			}).toList();

//		}
		
		for (ProductDetalisResponseDto v : list) {
			System.err.println(v.getImage() + " đay là hinh anh dto");
		}
		
		
		return ResponseEntity.ok(list);
	}

	@GetMapping("getProduct/{productCode}")
//	@JsonView(Views.Public.class)
	public ResponseEntity<?> getProduct(@PathVariable("productCode") String productCode) {
		Product product = productService.findByProductCode(productCode).get();
		
		product.setProductVersions(null);
		ProductResponseDto productResponseDto = productService.convertToProductResponseDto(product);
		
		return ResponseEntity.ok(productResponseDto);
	}

	@GetMapping("searchByQuery")
	public ResponseEntity<?> search(@RequestParam("query") String query, @RequestParam("page") Optional<Integer> page,
			@RequestParam("sort") int sort) {

		int curentPage = page.orElse(1);

		Sort sortObj = null;
		if (sort == 1) {
			sortObj = Sort.by("price").ascending();
		} else if (sort == 2) {
			sortObj = Sort.by("price").descending();

		} else {
			sortObj = Sort.by("price").ascending();
		}

		Pageable pageable = PageRequest.of(0, curentPage * 10, sortObj);

		Page<Product> pageProduct = query.equals("") ? productService.findAll(pageable)
				: productService.findByKeyword(query, pageable);

		
		List<ProductResponseDto> listProductResponseDtos  = pageProduct.getContent().stream().map(item->{
			ProductResponseDto productResponseDto = productService.convertToProductResponseDto(item);
			productResponseDto.setVersions(null);
			productResponseDto.setAttributes(null);
			productResponseDto.setDescriptions(null);
			productResponseDto.setDiscounts(null);
			
			return productResponseDto;
			
		}).toList();
		
		Page<ProductResponseDto> pageResult = new PageImpl(listProductResponseDtos,pageProduct.getPageable(),pageProduct.getTotalElements());
		return ResponseEntity.ok(pageResult);
	}
	
	@GetMapping("search")
	public ResponseEntity<?> search(@RequestParam("query") String query) {

	
		List<Product> listProduct = productService.findByKeyword(query);
		
		List<Product> firstFiveProducts = listProduct.subList(0, Math.min(listProduct.size(), 5));
		
		List<ProductResponseDto> listProductResponseDtos = firstFiveProducts.stream().map(item->{
			ProductResponseDto productResponseDto = new ProductResponseDto();
			
			BeanUtils.copyProperties(item, productResponseDto);
			productResponseDto.setDiscountedPrice(item.getDiscountedPrice());
			productResponseDto.setCategoryCode(item.getCategoryCode());
			return productResponseDto;
			
		}).toList();
		
		return ResponseEntity.ok(listProductResponseDtos);
	}
	
	@GetMapping("check-detailsCode/{detailsCode}")
	public ResponseEntity<?> checkDetailsCode(@PathVariable("detailsCode") String detailsCode){
		boolean check = true;
		
		Product product = productService.findByProductCode(detailsCode).orElse(null);
		
		if(product!=null) {
			check = false;
		}
		
		return ResponseEntity.ok(check);
	}
	
	@GetMapping("getListProductRand/{number}")
	public ResponseEntity<?> getListProductRand(@PathVariable("number")int number){
		List<Product> lisProducts = productService.findRandomProducts(number);
		
		List<ProductResponseDto> listProductResponseDtos= lisProducts.stream().map(item->{
			ProductResponseDto productResponseDto = new ProductResponseDto();
			
			BeanUtils.copyProperties(item, productResponseDto);
			
			return productResponseDto;
		}).toList();
		
		return ResponseEntity.ok(listProductResponseDtos);
	}
	
	@GetMapping("getAllFeedBack/{productCode}")
	public ResponseEntity<?> getAllFeedBack(@PathVariable("productCode")String productCode,@RequestParam("page")Optional<Integer> page){
		int currentPage = page.orElse(1);
		
		Pageable pageable = PageRequest.of(currentPage-1, 3);
		
		Page<FeedBack> pageFeedBack = feedBackService.findAllByProduct(productCode, pageable);
		
		List<FeedBackResponseDto> lisFeedBackResponseDtos = pageFeedBack.getContent().stream().map(item->{
			FeedBackResponseDto feedBackResponseDto = new FeedBackResponseDto();
			
			BeanUtils.copyProperties(item, feedBackResponseDto);
			feedBackResponseDto.setUsername(item.getUser().getUsername());
			
			if(item.getFeedBackImages()!=null) {
				List<String> images = item.getFeedBackImages().stream().map(FeedBackImage::getImage).toList();
				
				feedBackResponseDto.setImages(images);
			}
			
			return feedBackResponseDto;
		}).toList();
		
		
		Page<FeedBackResponseDto> pageResult = new PageImpl<>(lisFeedBackResponseDtos, pageFeedBack.getPageable(), pageFeedBack.getTotalElements());
		
		return ResponseEntity.ok(pageResult);
	}

}
