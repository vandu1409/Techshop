package com.vandu.controller.site;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.VoucherApplicationResultDTO;
import com.vandu.dto.VoucherDto;
import com.vandu.enums.DiscountType;
import com.vandu.helper.UserHelper;
import com.vandu.model.Cart;
import com.vandu.model.ProductDiscount;
import com.vandu.model.User;
import com.vandu.model.Voucher;
import com.vandu.service.CartService;
import com.vandu.service.UserService;
import com.vandu.service.VoucherService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("voucher")
public class VoucherSiteController {

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;

	@GetMapping("getValidVoucher")
	public ResponseEntity<?> getValidVoucher() {
		return ResponseEntity.ok(voucherService.findValidVouchers().stream().map(item -> {
			VoucherDto voucherDto = new VoucherDto();
			BeanUtils.copyProperties(item, voucherDto);
			return voucherDto;
		}).toList());
	}

	@GetMapping("applyVoucherCode/{voucherId}")
	public ResponseEntity<?> applyVoucherCode(@PathVariable("voucherId") Long voucherId) {
		User user = UserHelper.getCurrentUser(request, userService);
		VoucherApplicationResultDTO voucherResult = voucherService.applyVoucher(voucherId,user.getUserId());

		return ResponseEntity.ok(voucherResult);

	}

}
