package com.vandu.web.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vandu.model.Brand;
import com.vandu.model.Category;
import com.vandu.model.Product;
import com.vandu.model.ProductSeries;
import com.vandu.service.BrandService;
import com.vandu.service.CategoryService;
import com.vandu.service.ProductSeriesService;
import com.vandu.service.ProductService;

@Controller
@RequestMapping("vandustore")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductSeriesService productSeriesService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping()
	public String getAll() {
		return "site/category";
	}

	@ModelAttribute("listBrand")
	public List<Brand> getAllBrand() {
		return brandService.findAll();
	}

	@ModelAttribute("listRand")
	List<Product> getListRand() {
		return productService.findRandomProducts(6);
	}
	
	@ModelAttribute("listCategory")
	public List<Category> getAllCategory(){
		return categoryService.findAll();
	}

	@GetMapping("{categoryCode}")
	public String getCategory(@PathVariable("categoryCode") String categoryCode, ModelMap modelMap) {
		Category category = categoryService.findByCategoryCode(categoryCode).orElse(null);

		if (category != null) {
			modelMap.addAttribute("categoryCode", category.getCategoryCode());
			return "site/category";
		}

		return "404";
	}

	@GetMapping("{categoryCode}/{productCode}")
	public String productDetails(@PathVariable("productCode") String productCode,
			@PathVariable("categoryCode") String categoryCode, ModelMap modelMap) {
//		Product product = productService.findByProductCode(productCode).orElse(null);

		System.err.println("P " + productCode + "");
		Category category = categoryService.findByCategoryCode(categoryCode).orElse(null);

		if (category != null) {
			modelMap.addAttribute("categoryCode", category.getCategoryCode());
		}

		Product product = productService.findByProductCode(productCode).orElse(null);

		if (product != null) {
			modelMap.addAttribute("productCode", productCode);
			return "site/product";
		}

		ProductSeries productSeries = productSeriesService.findBySeriesCode(productCode).orElse(null);

		if (productSeries != null) {

			modelMap.addAttribute("seriesCode", productCode);
			modelMap.addAttribute("brandCode", productSeries.getBrand().getBrandCode());
			return "site/category";
		}

		Brand brand = brandService.findByBrandCode(productCode).orElse(null);

		if (brand != null) {
			modelMap.addAttribute("brandCode", productCode);
			return "site/category";
		}

		return "404";

	}

	

}
