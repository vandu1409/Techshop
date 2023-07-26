package com.vandu.controller.admin;

import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.VoucherDto;
import com.vandu.enums.DiscountType;
import com.vandu.model.Voucher;
import com.vandu.service.VoucherService;

@RestController
@RequestMapping("admin/voucher")
public class VoucherApiController {

	@Autowired
	private VoucherService voucherService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<?> saveOrUpdate(@RequestBody VoucherDto voucherDto) {

		Voucher voucher = new Voucher();

		BeanUtils.copyProperties(voucherDto, voucher);

		voucher.setDiscountType(
				voucherDto.getDiscountType() == 1 ? DiscountType.DISCOUNTBYPRICE : DiscountType.DISCOUNTBYPERCENT);

		Voucher entity = voucherService.save(voucher);

		BeanUtils.copyProperties(entity, voucherDto);

		return ResponseEntity.ok(voucherDto);
	}

	@GetMapping("getVouchers")
	public ResponseEntity<Page<Voucher>> getVoucherByStatus(
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "3") int size) {

		Pageable pageable = PageRequest.of(page-1, size);
		

		Page<Voucher> list = voucherService.findByStatus(pageable, status);
		System.err.println(status);
		for (Voucher voucher : list) {
			System.err.println(voucher.getName());
		}

		return ResponseEntity.ok(list);

	}
	
	@GetMapping("edit/{voucherId}")
	public ResponseEntity<VoucherDto> edit(@PathVariable("voucherId")Long id){
		
		return ResponseEntity.ok(voucherService.findVoucherDtoById(id));
		
	}
	
	@DeleteMapping("delete/{voucherId}")
	public ResponseEntity<?> delete(@PathVariable("voucherId")Long id){
		voucherService.deleteById(id);
		
		return ResponseEntity.ok("Success");
	}
}
