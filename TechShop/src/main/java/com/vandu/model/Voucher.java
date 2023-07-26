package com.vandu.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vandu.enums.DiscountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long voucherId;
	
	private String voucherCode;
	
	@Column(columnDefinition = "nvarchar(300)")
	private String name;
	
	private Double discountValue;
	
	@Temporal(TemporalType.DATE)
	private LocalDate startDate;

	@Temporal(TemporalType.DATE)
	private LocalDate endDate;
	
	private Double amountApplied; // số tiền tối thiểu để voucher được áp dụng
	
	private Double maxValue;//số tiền giảm giá tối đa
	
	@Column(columnDefinition = "nvarchar(3000)")
	private String notes;
	
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;
	
	@OneToMany(mappedBy = "voucher")
	@JsonIgnore
	private List<Order> orders;
	

}
