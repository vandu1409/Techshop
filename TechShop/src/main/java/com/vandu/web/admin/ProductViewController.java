package com.vandu.web.admin;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vandu.dto.BrandDto;
import com.vandu.dto.CategoryDto;
import com.vandu.dto.ColorDto;
import com.vandu.dto.DiscountDto;
import com.vandu.dto.FeatureDto;
import com.vandu.dto.ProductDto;
import com.vandu.model.Brand;
import com.vandu.model.Category;
import com.vandu.model.Product;
import com.vandu.model.ProductSeries;
import com.vandu.service.BrandService;
import com.vandu.service.CategoryService;
import com.vandu.service.ColorService;
import com.vandu.service.DiscountService;
import com.vandu.service.FeatureService;
import com.vandu.service.ProductSeriesService;
import com.vandu.service.ProductService;

@Controller
@RequestMapping("admin/product")
public class ProductViewController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductSeriesService productSeriesService;

	@Autowired
	private DiscountService discountService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FeatureService featureService;
	
	@Autowired
	private ColorService colorService;

	@ModelAttribute("listBrand")
	public List<BrandDto> getAllBrand() {

		return brandService.findAll().stream().map(item -> {
			BrandDto brandDto = new BrandDto();

			BeanUtils.copyProperties(item, brandDto);
			brandDto.setBrandName(item.getBrandName());

			return brandDto;
		}).toList();
	}

	@ModelAttribute("listCategory")
	public List<CategoryDto> getAllCategory() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDto categoryDto = new CategoryDto();

			BeanUtils.copyProperties(item, categoryDto);

			return categoryDto;
		}).toList();
	}
	
	@ModelAttribute("listFeature")
	public List<FeatureDto> getAllFeature(){
		return featureService.findAll().stream().map(item->{
			FeatureDto featureDto = new FeatureDto();
			
			BeanUtils.copyProperties(item, featureDto);
			
			return featureDto;
		}).toList();
	}
	
	@ModelAttribute("listColor")
	public List<ColorDto> getAllColor(){
	 return	colorService.findAll().stream().map(item->{
			ColorDto colorDto = new ColorDto();
			
			BeanUtils.copyProperties(item, colorDto);
			
			return colorDto;
		}).toList();
	}
//
//	@GetMapping("add")
//	public String add(ModelMap modelMap) {
//		ProductDto productDto = (ProductDto) modelMap.get("productDto");
//
//		if (productDto != null) {
//			productDto.setEdit(false);
//			System.err.println(productDto.getProductId()+"là product ì");
//			modelMap.addAttribute("productDto", productDto);
//		} else {
//			ProductDto product = new ProductDto();
//			product.setEdit(true);
//			modelMap.addAttribute("productDto", product);
//		}
//		return "admin/product";
//	}
//

//
	@GetMapping("edit/{productId}")
	public String edit(@PathVariable("productId") Long id,ModelMap modelMap) {
		Product product = productService.findById(id).orElse(null);
		
		ProductDto productDto = new ProductDto();
		
		BeanUtils.copyProperties(product, productDto);
		productDto.setFeatureId(product.getFeature().getId());
		productDto.setBrandId(product.getProductSeries().getBrand().getBrandId());
		productDto.setEdit(true);
		
		modelMap.addAttribute("productDto",productDto);
		
		modelMap.addAttribute("productId",id);
		return "admin/addProduct";

	}
	
	@GetMapping("list")
	public String list() {
		return "admin/listProduct";

	}

	@GetMapping("saveOrUpdate")
	public String saveOrUpdate(ModelMap modelMap) {
		ProductDto productDto = new ProductDto();
		productDto.setEdit(false);
		
		modelMap.addAttribute("productDto",productDto);
		return "admin/addProduct";
	}

}
