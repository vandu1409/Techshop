package com.vandu.web.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.vandu.model.Brand;
import com.vandu.model.Category;
import com.vandu.model.Menu;
import com.vandu.model.Product;
import com.vandu.service.BrandService;
import com.vandu.service.CategoryService;
import com.vandu.service.MenuService;
import com.vandu.service.ProductSeriesService;
import com.vandu.service.ProductService;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductSeriesService productSeriesService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MenuService menuService;

//	@GetMapping()
//	public String getAll() {
//		return "site/category";
//	}

//	@ExceptionHandler(NoHandlerFoundException.class)
//	public String handleNotFoundError() {
//		System.out.println("dsfsdfdhf");
//		return "/site/404";
//	}

	@ModelAttribute("listPhones")
	List<Product> getListPhones() {
		return productService.findRandomByCategoryCode(6, "dien-thoai");
	}

	@ModelAttribute("listBrand")
	public List<Brand> getAllBrand() {
		return brandService.findAll();
	}

	@ModelAttribute("listRand")
	List<Product> getListRand() {
		return productService.findRandomProducts(12);
	}

	@ModelAttribute("listCategory")
	public List<Category> getAllCategory() {
		return categoryService.findAll();
	}

	@ModelAttribute("listMenu")
	public List<Menu> getAllMenu() {
		return menuService.findAll();
	}
}
