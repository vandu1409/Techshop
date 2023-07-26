package com.vandu.controller.admin;

import java.util.Date;
import java.util.List;

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

import com.vandu.dto.DiscountDto;
import com.vandu.enums.DiscountType;
import com.vandu.model.Discount;
import com.vandu.model.Voucher;
import com.vandu.service.DiscountService;

@RestController
@RequestMapping("admin/discount")
public class DiscountApiController {
	
	@Autowired
	private DiscountService discountService;
	
	@PostMapping("saveOrUpdate")
	public ResponseEntity<DiscountDto> saveOrUpdate(@RequestBody DiscountDto discountDto){
		Discount discount = new Discount();
		
		BeanUtils.copyProperties(discountDto, discount);
		discount.setDiscountType(
				discountDto.getDiscountType() == 1 ? DiscountType.DISCOUNTBYPRICE : DiscountType.DISCOUNTBYPERCENT);
		
		Discount entity = discountService.save(discount);
		
		BeanUtils.copyProperties(entity, discountDto);
		
		return ResponseEntity.ok(discountDto);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		Discount discount  = discountService.findById(id).orElse(new Discount());
		
		if(discount!=null) {
			discountService.delete(discount);
		}
		
		
		return ResponseEntity.ok("Success");
	}
	
	@GetMapping("edit/{id}")
	public ResponseEntity<DiscountDto> findById(@PathVariable("id") Long id){
	
		
		return ResponseEntity.ok(discountService.findDiscountDtoById(id));
	}
	
	@GetMapping("getDiscounts")
	public ResponseEntity<Page<Discount>> getDiscounts(
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "3") int size) {

		Pageable pageable = PageRequest.of(page-1, size);
		Date today = new Date();

		Page<Discount> list = discountService.findByStatus(today, pageable, status);
		System.err.println(status);
		

		return ResponseEntity.ok(list);

	}
	
	@GetMapping("getValidDiscounts")
	public ResponseEntity<List<DiscountDto>> getValidDiscounts(){
		return ResponseEntity.ok(discountService.findValidDicounts().stream().map(item->{
			DiscountDto discountDto = new DiscountDto();
			BeanUtils.copyProperties(item, discountDto);
			return discountDto;
		}).toList());
	}

}
