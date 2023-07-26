package com.vandu.web.site;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vandu.enums.TokenType;
import com.vandu.model.Category;
import com.vandu.model.Token;
import com.vandu.model.User;
import com.vandu.service.CategoryService;
import com.vandu.service.TokenService;
import com.vandu.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserSiteViewController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	@ModelAttribute("listCategory")
	public List<Category> getAllCategory() {
		return categoryService.findAll();
	}

	@GetMapping("login")
	public String login(ModelMap modelMap, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//		String errorMessage = (String) modelMap.get("errorMessage");
//		modelMap.addAttribute("errorMessage",errorMessage);

//		if(authentication!=null && authentication.isAuthenticated()) {
//			return "redirect:/";
//		}

		System.err.println(session.getAttribute("errorMessage"));

		modelMap.addAttribute("errorMessage", session.getAttribute("errorMessage"));

		session.removeAttribute("errorMessage");

		return "site/login";
	}

	@GetMapping("oauth2/google")
	@ResponseBody
	public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
		return oAuth2User.getAttributes();
	}

	@GetMapping("dang-ky")
	public String register() {
		return "site/register";
	}

	@GetMapping("confirm/{token}")
	public String confirm(@PathVariable("token") String token, ModelMap map) {
		User user = tokenService.findUserByToken(token, TokenType.REGISTER).orElse(null);
		Token currentToken = tokenService.findByTokenAndTokenType(token, TokenType.REGISTER).orElse(null);

		if (user != null && currentToken != null) {
//			LocalDate now = LocalDate.now();
//			LocalDate expiredDate = currentToken.getExpiredDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
//			if(now.isBefore(expiredDate)) {
				if(user.isActive()) {
					map.addAttribute("message","Tài khoản đã được xác thực trước đó!");
				}else {
					user.setActive(true);
					userService.update(user);
					map.addAttribute("message","Xác thực tài khoản thành công!");
					tokenService.delete(currentToken);
				}
//			}else {
//				map.addAttribute("message","!");
//			}
			

		} else {
			map.addAttribute("message", "Tài khoản đã được xác thực trước đó!");
		}

		return "site/confirm";

	}
	
	@GetMapping("quen-mat-khau")
	public String forgetPassword() {
		return "site/forgetpassword";
	}

	@GetMapping("change-password/{token}")
	public String changePassword(@PathVariable("token") String token, ModelMap map) {
		map.addAttribute("token",token);
		return "site/forget-password";

	}
}
