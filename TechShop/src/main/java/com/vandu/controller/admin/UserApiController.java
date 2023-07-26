package com.vandu.controller.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.UserDto;
import com.vandu.dto.response.UserResponseDto;
import com.vandu.enums.AuthenticationType;
import com.vandu.enums.Role;
import com.vandu.exception.DuplicateDataException;

import com.vandu.model.User;

import com.vandu.service.UserService;

@RestController
@RequestMapping("admin/user")
public class UserApiController {

	@Autowired
	private UserService userService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> save(@RequestBody UserDto userDto) {
		User user = new User();

		Role role = Role.ROLE_USER;

		if (userDto.getRole() == 2) {
			role = Role.ROLE_SUPERADMIN;
		} else if (userDto.getRole() == 1) {
			role =Role.ROLE_ADMIN;
		} 

		BeanUtils.copyProperties(userDto, user);
		user.setAuthenticationType(AuthenticationType.LOCAL);
		user.setActive(true);
		user.setRole(role);

		if (userDto.getUserId() != null) {

			User currentUser = userService.findById(user.getUserId()).orElseThrow();
			user.setPassword(currentUser.getPassword());

			user.setUpdateDate(new Date());
			
			userService.update(user);
		}else {
			user.setCreateDate(new Date());
			userService.save(user);
		}

		

		return ResponseEntity.ok("sucess");

	}

	@GetMapping("find/{userId}")
	public ResponseEntity<UserDto> findById(@PathVariable("userId") Long userId) {
		User user = userService.findById(userId).orElseThrow();
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(user, userDto);
		userDto.setPassword(null);
		
		if(user.getRole().equals(Role.ROLE_SUPERADMIN)) {
			userDto.setRole(2);
		}else if(user.getRole().equals(Role.ROLE_ADMIN)) {
			userDto.setRole(1);
		}else {
			userDto.setRole(0);
		}

		return ResponseEntity.ok(userDto);

	}

	@PutMapping("delete/{userId}")
	public ResponseEntity<String> delete(@PathVariable("userId") Long userId) {
		User user = userService.findById(userId).orElseThrow();

		if (user != null) {
			user.setDeleted(true);
			userService.update(user);
		}

		return ResponseEntity.ok("Success");
	}

	@PutMapping("restore/{userId}")
	public ResponseEntity<?> restoreUser(@PathVariable("userId") Long userId){
		User user = userService.findById(userId).orElse(null);
		
		if(user!=null && user.isDeleted()) {
			user.setDeleted(false);
			userService.update(user);
		}
		
		return ResponseEntity.ok("restore Success");
	}

	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("keyword") String keyword,
			@RequestParam("status") int status) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		boolean isDeleted = status == 1;

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<User> pageUser = keyword.equals("") ? userService.findAll(isDeleted, pageable)
				: userService.findByUsernameContainingAndIsDeleted(keyword, isDeleted, pageable);

		List<UserResponseDto> listUserResponseDtos = pageUser.getContent().stream().map(item -> {
			UserResponseDto userResponseDto = new UserResponseDto();

			BeanUtils.copyProperties(item, userResponseDto);

			return userResponseDto;
		}).toList();

		Page<UserResponseDto> pageResult = new PageImpl<>(listUserResponseDtos, pageUser.getPageable(),
				pageUser.getTotalElements());

		return ResponseEntity.ok(pageResult);

	}


}
