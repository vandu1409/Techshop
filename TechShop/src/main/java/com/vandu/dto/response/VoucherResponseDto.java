package com.vandu.dto.response;

import java.time.LocalDate;

import com.vandu.enums.DiscountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherResponseDto {

	private String voucherCode;
	
	private String name;
	
	private Double discountValue;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private Double amountApplied; // số tiền tối thiểu để voucher được áp dụng
	
	private Double maxValue;//số tiền giảm giá tối đa
	
	private String notes;
	

	private DiscountType discountType;
	

}
