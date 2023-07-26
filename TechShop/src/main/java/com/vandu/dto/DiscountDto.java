package com.vandu.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
	private Long id;

	private String discountCode;

	private String name;

	private Double discountValue;
	
	private int discountType;
	
	private Double amountApplied; // số tiền tối thiểu để voucher được áp dụng
	
	private Double maxValue;//số tiền giảm giá tối đa

	private LocalDate startDate;

	private LocalDate endDate;
	
	private String notes;
}
