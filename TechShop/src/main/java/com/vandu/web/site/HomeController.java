package com.vandu.web.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vandu.model.Brand;
import com.vandu.model.Category;
import com.vandu.model.Product;
import com.vandu.model.User;
import com.vandu.service.BrandService;
import com.vandu.service.CategoryService;
import com.vandu.service.ProductService;
import com.vandu.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CategoryService categoryService;


//	@Autowired
//	OAuth2UserRequest oAuth2UserRequest;

//	@ModelAttribute("listProduct")
//	List<Product> getAll() {
//		return productService.findAll();
//	}
//	
//	@ModelAttribute("listRandom")
//	List<Product> getListRand(){
//		return productService.findRandomByCategoryCode(8,"dien-thoai");
//	}
//	
//	@ModelAttribute("listPhones")
//	List<Product> getListPhones(){
//		return productService.findRandomByCategoryCode(10,"dien-thoai");
//	}
//	
//	@ModelAttribute("listsuggest")
//	List<Product> getListSuggest(){
//		return productService.findRandomProducts(10);
//	}
//
//	@ModelAttribute("listBrand")
//	public List<Brand> getAllBrand() {
//		return brandService.findAll();
//	}
//	
//	@ModelAttribute("listCategory")
//	public List<Category> getAllCategory(){
//		return categoryService.findAll();
//	}
//	
//	@ModelAttribute("listLaptop")
//	List<Product> getListLaptop(){
//		return productService.findRandomByCategoryCode(10,"lap-top");
//	}
//	
//	@ModelAttribute("listSmartWatch")
//	List<Product> getListSmartWatch(){
//		return productService.findRandomByCategoryCode(10,"smart-watch");
//	}
//	
	@GetMapping()
	public String home(HttpSession session) {
		
		System.err.println("session "+session.getAttribute("loggedUser"));
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	
		
		if (authentication != null && authentication.isAuthenticated()) {
			String name = authentication.getName();
			Object principal = authentication.getPrincipal();
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				// Lấy các thông tin của user đã đăng nhập từ userDetails
				String username = userDetails.getUsername();
				String email = ((User) userDetails).getEmail();
				Long userId = ((User) userDetails).getUserId();
				System.err.println("email : " + email+"User Id"+userId);

			}
			if (principal instanceof DefaultOAuth2User) {
				OAuth2User oauth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();
				String username = oauth2User.getAttribute("email");
				
			}

		}

		return "site/index";
	}

}
