package com.vandu.controller.site;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.vandu.dto.ChangePasswordDto;
import com.vandu.dto.RegisterDto;
import com.vandu.dto.response.UserResponseDto;
import com.vandu.exception.PasswordMismatchException;
import com.vandu.helper.UserHelper;
import com.vandu.model.User;
import com.vandu.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("profile")
public class ProfileApiController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

//	@PostMapping("change-password")
//	public ResponseEntity<?> changePassword(@)

	@GetMapping("getCurrentUser")
	public ResponseEntity<?> getCurrentUser() {
		User user = UserHelper.getCurrentUser(request, userService);

		UserResponseDto userResponseDto = new UserResponseDto();

		BeanUtils.copyProperties(user, userResponseDto);

		return ResponseEntity.ok(userResponseDto);
	}

//	@PostMapping("thay-doi-mat-khau")
//	public ResponseEntity<?> changePassword(RegisterDto registerDto){
//		User user  = UserHelper.getCurrentUser(request, userService);
//		
//		if(user!=null ) {
//			if(registerDto.getRepassword().equalsIgnoreCase(registerDto.getPassword())) {
//				user.setPassword(registerDto.getPassword());
//			}
//		}
//	}

	@PutMapping("thay-doi-thong-tin")
	public ResponseEntity<?> changeInfo(@RequestBody RegisterDto registerDto) {
		User user = UserHelper.getCurrentUser(request, userService);

		if (user != null) {
			user.setEmail(registerDto.getEmail());
			user.setFullname(registerDto.getFullname());

			userService.save(user);
		}

		return ResponseEntity.ok("success");
	}

	@PutMapping("doi-mau-khau")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
		try {
			User user = UserHelper.getCurrentUser(request, userService);
			System.err.println(user.getPassword());
			if (user != null && changePasswordDto.getCurrentPassword() !=null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				boolean passwordMatches = passwordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword());

				if (!passwordMatches) {
					throw new PasswordMismatchException("Mật khẩu hiện tại không đúng!");
				}

				if (!changePasswordDto.getPassword().equalsIgnoreCase(changePasswordDto.getRepassword())) {
					throw new PasswordMismatchException("Mật không không trùng khớp!");
				}

				user.setPassword(changePasswordDto.getPassword());
				userService.save(user);
			}
			
			return ResponseEntity.ok("success");
		} catch (PasswordMismatchException e) {
			throw e;
		}
	}
}
