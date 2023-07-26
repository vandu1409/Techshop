package com.vandu.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vandu.enums.DiscountType;
import com.vandu.helper.Views;

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
@JsonView(Views.Public.class)
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Admin.class)
	private Long id;
	
	private String discountCode;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String name;
	
	private Double discountValue;
	
	private Double amountApplied; // số tiền tối thiểu để voucher được áp dụng
	
	private Double maxValue;//số tiền giảm giá tối đa
	
	@Temporal(TemporalType.DATE)
	private LocalDate  startDate;
	
	@Temporal(TemporalType.DATE)
	private LocalDate  endDate;
	
	@Column(columnDefinition = "nvarchar(3000)")
	private String notes;
	
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;
	
	@OneToMany(mappedBy = "discount")
	@JsonIgnore
	private List<ProductDiscount> productDiscounts;
	
	private transient double discountAmount;
	
	
	public Boolean isVoucherValid() {
		  LocalDate now = LocalDate.now();
		 
		  return !now.isBefore(this.getStartDate()) && !now.isAfter(this.getEndDate());
	} 
	
	
	
}
